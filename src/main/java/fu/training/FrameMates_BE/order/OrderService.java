package fu.training.FrameMates_BE.order;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.orderdetail.OrderDetail;
import fu.training.FrameMates_BE.orderdetail.OrderDetailMapper;
import fu.training.FrameMates_BE.orderdetail.OrderDetailModel;
import fu.training.FrameMates_BE.orderdetail.OrderDetailService;
import fu.training.FrameMates_BE.share.exceptions.InvalidStatusStringException;
import fu.training.FrameMates_BE.share.exceptions.MissingBearerTokenException;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import fu.training.FrameMates_BE.share.helpers.EnumConverter;
import fu.training.FrameMates_BE.share.helpers.PaginationResponse;
import fu.training.FrameMates_BE.slotbooking.SlotBooking;
import fu.training.FrameMates_BE.slotbooking.SlotBookingService;
import fu.training.FrameMates_BE.studio.Studio;
import fu.training.FrameMates_BE.studio.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class  OrderService {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    public StudioService studioService;
    @Autowired
    public SlotBookingService slotBookingService;
    public OrderModel createOrder(OrderModel orderModel, Authentication authentication){
        if(authentication == null) throw new MissingBearerTokenException();
        var currentCustomer = (Account) authentication.getPrincipal();
        var creatingOrder = orderMapper.fromCreateModelToEntity(orderModel);
        var orderStudio = studioService.getById(orderModel.getStudioId());
        creatingOrder.setAccount(currentCustomer);
        creatingOrder.setStudio(orderStudio);
        creatingOrder.setStatus(OrderStatus.PENDING.ordinal());
        creatingOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        List<OrderDetailModel> orderDetailModels = orderModel.getOrderDetails().stream().toList();
        Set<OrderDetail> orderDetails = new HashSet<>();
        int totalPrice = 0;
        var createdOrder = orderRepository.save(creatingOrder);

        for (int i = 0; i < orderDetailModels.size(); i++) {
            var orderDetailModel = orderDetailModels.get(i);
            var orderDetailEntity = orderDetailMapper.toEntity(orderDetailModel);
            orderDetailEntity.setOrder(createdOrder);
            orderDetails.add(orderDetailEntity);
            SlotBooking slotBooking = slotBookingService.getSlotBookingEntityById(orderDetailModel.getSlotBookingId());

            orderDetailEntity.setStartTime(slotBooking.getStartTime());
            orderDetailEntity.setEndTime(slotBooking.getEndTime());
            orderDetailEntity.setPrice(slotBooking.getPrice());


            orderDetailEntity.setSlotBooking(slotBooking);
            totalPrice += slotBooking.getPrice();
            orderDetailService.createOrderDetails(orderDetailEntity);
        }
        creatingOrder.setTotalPrice(totalPrice);
        creatingOrder.setStatus(OrderStatus.PENDING.ordinal());
        createdOrder = orderRepository.save(creatingOrder);
        return orderMapper.toModel(createdOrder);
    };

    public List<OrderModelIncludeStudio> getOrdersByCurrentCustomer(Account account){
        var orders = orderRepository.findByAccount_AccountId(account.getAccountId());
        List<OrderModelIncludeStudio> ordersModel = orders.stream().map(orderMapper::toModelIncludeStudio).toList();
        return ordersModel;
    }

    public List<OrderModelIncludeStudio> getOrdersByCurrentStudio(Account account){
        var orders = orderRepository.findByAccount_Studio_StudioId(account.getStudio().getStudioId());
        List<OrderModelIncludeStudio> ordersModel = orders.stream().map(orderMapper::toModelIncludeStudio).toList();
        return ordersModel;
    }
    public OrderModel getOrderById(int id) {
        var order = getOrderEntityById(id);
        return orderMapper.toModel(order);
    }
    public Order getOrderEntityById(int id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Order id: " + id + " not found"));
    }

    public List<OrderModelIncludeStudio> getOrdersByCurrentUser(Authentication authentication) {
        if(authentication == null) throw new MissingBearerTokenException();
        var currentUser = (Account) authentication.getPrincipal();
        if(currentUser.getRole().equals("CUS")){
            return getOrdersByCurrentCustomer(currentUser);
        }
        return getOrdersByCurrentStudio(currentUser);
    }

    public void cancelOrder(int orderId, Authentication authentication) {
        if(authentication == null) throw new MissingBearerTokenException();
        var order = getOrderEntityById(orderId);
        var currentAccount = (Account) authentication.getPrincipal();
        if(!Objects.equals(order.getAccount().getAccountId(), currentAccount.getAccountId()))
            throw new RecordNotFoundException("You are not allowed to cancel this order!");
        order.setStatus(OrderStatus.CANCEL.ordinal());
        orderRepository.save(order);
    }

    public void changeOrderStatus(int id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Order id: " + id + " not found!!"));
        var statusValue = EnumConverter.convertStringToEnumValue(status, OrderStatus.class);
        order.setStatus(statusValue);
        orderRepository.save(order);
    }

//    public void updateOrderStatus(int id, String status) throws RecordNotFoundException, InvalidStatusStringException;
//
//    public OrderModel getOrderById(int id) throws RecordNotFoundException;
//
//    public List<OrderModel> getOrderByUser(Authentication authentication);
//    public PaginationResponse<OrderModel> getOrdersByStatus(String status, Pageable pageable, Authentication authentication) throws InvalidStatusStringException;
//
//    public List<OrderModel> getFeedbackByStudioId(Integer studioId);
//
//    public List<OrderModel> getOrdersByCurrentUserIncludeOrderDetails(Authentication authentication);
}

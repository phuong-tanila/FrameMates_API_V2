package fu.training.FrameMates_BE.orderdetail;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.order.Order;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import fu.training.FrameMates_BE.slotbooking.SlotBooking;
import fu.training.FrameMates_BE.slotbooking.SlotBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private SlotBookingService slotBookingService;
    @Autowired
    private OrderDetailMapper mapper;
//    OrderDetail createOrderDetails(OrderDetail orderDetail);
//
//    List<OrderDetailModel> getFeedbacksByStudioId(int studioId);
//    List<OrderDetailModel> getFeedbacksByServiceId(int studioId);
//    List<OrderDetailModel> getFeedbacksByOrderId(int orderId);
//    List<OrderDetailModel> getOrderDetailsByOrderId(int orderId);
//    OrderDetailModel createFeedBack(OrderDetailModel model, Authentication authentication) throws IllegalAccessException;
    public OrderDetail createOrderDetails(OrderDetail orderDetail){

        return repository.save(orderDetail);
    }

    public void createFeedBack(int orderDetailId, Authentication authentication, OrderDetailModel model) {
        var currentAccount = (Account) authentication.getPrincipal();
        var orderDetail = repository
                .findByOrderDetailIdAndAndOrder_Account_AccountId(orderDetailId, currentAccount.getAccountId())
                .orElseThrow(() -> new RecordNotFoundException("Order detail id:" + orderDetailId + " not found!"));
        orderDetail.setRating(model.getRating());
        orderDetail.setContent(model.getContent());
        orderDetail.setPostDate(new Timestamp(System.currentTimeMillis()));
        repository.save(orderDetail);
    }

    public List<OrderDetailModel> getFeedbackByCurrentStudio(Authentication authentication) {
        var currentAccount = (Account) authentication.getPrincipal();
        var currentStudio = currentAccount.getStudio();
        if(currentAccount.getStudio() == null || currentStudio.deleted) throw new RecordNotFoundException("You must owned a studio to do this feature");
        log.debug(String.valueOf(currentStudio.getStudioId()));
        var feedbackList =  repository.findOrderDetailByRatingNotNullAndSlotBooking_Studio_StudioId(currentStudio.getStudioId());
                return feedbackList.stream().map(mapper::toModel).toList();

    }
}

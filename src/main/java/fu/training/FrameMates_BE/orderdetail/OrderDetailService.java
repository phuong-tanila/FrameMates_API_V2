package fu.training.FrameMates_BE.orderdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;
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
}

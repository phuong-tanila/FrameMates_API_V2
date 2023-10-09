package fu.training.FrameMates_BE.orderdetail;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderDetailService {
    OrderDetail createOrderDetails(OrderDetail orderDetail);

    List<OrderDetailModel> getFeedbacksByStudioId(int studioId);
    List<OrderDetailModel> getFeedbacksByServiceId(int studioId);
    List<OrderDetailModel> getFeedbacksByOrderId(int orderId);
    List<OrderDetailModel> getOrderDetailsByOrderId(int orderId);
    OrderDetailModel createFeedBack(OrderDetailModel model, Authentication authentication) throws IllegalAccessException;
}

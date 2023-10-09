package fu.training.FrameMates_BE.order;

import fu.training.FrameMates_BE.share.exceptions.InvalidStatusStringException;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import fu.training.FrameMates_BE.share.helpers.PaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {
    OrderModel createOrder(OrderModel orderModel) throws RecordNotFoundException;

    void updateOrderStatus(int id, String status) throws RecordNotFoundException, InvalidStatusStringException;

    OrderModel getOrderById(int id) throws RecordNotFoundException;

    List<OrderModel> getOrderByUser(Authentication authentication);
    PaginationResponse<OrderModel> getOrdersByStatus(String status, Pageable pageable, Authentication authentication) throws InvalidStatusStringException;

    List<OrderModel> getFeedbackByStudioId(Integer studioId);

    List<OrderModel> getOrdersByCurrentUserIncludeOrderDetails(Authentication authentication);
}

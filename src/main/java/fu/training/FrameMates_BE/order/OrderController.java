package fu.training.FrameMates_BE.order;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.share.exceptions.InvalidStatusStringException;
import fu.training.FrameMates_BE.share.exceptions.MissingBearerTokenException;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import fu.training.FrameMates_BE.share.helpers.PaginationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
//    @Secured({"ROLE_OWNER"})
//    public List<OrderModel> getOrdersByCurrentStudio(
//            Authentication authentication
//    ){
//        return orderService.getOrdersByCurrentStudio(authentication);
//    }

    @Secured({"ROLE_CUS"})
    @PostMapping
    public ResponseEntity createOrder(
            @RequestBody OrderModel orderModel,
            Authentication authentication
    ) throws URISyntaxException {
        var createdOrder = orderService.createOrder(orderModel, authentication);
        return new ResponseEntity(createdOrder, HttpStatus.CREATED);
    }
//    @Secured({"ROLE_CUS", "ROLE_OWNER"})
    @GetMapping("{id}")
    public ResponseEntity<OrderModel> getOrderById(
            Authentication authentication,
            @PathVariable int id
    ){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping
    public ResponseEntity<List<OrderModelIncludeStudio>> getOrdersByCurrentUser(
            Authentication authentication
    ){
        return ResponseEntity.ok(orderService.getOrdersByCurrentUser(authentication));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity changeOrderStatus(
            Authentication authentication,
            @PathVariable int id,
            @PathVariable String status
    ){


        orderService.changeOrderStatus(id, status);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{orderId}")
    public ResponseEntity cancelOrder(
            Authentication authentication,
            @PathVariable int orderId
    ){
        orderService.cancelOrder(orderId, authentication);
        return ResponseEntity.ok().build();
    }
}

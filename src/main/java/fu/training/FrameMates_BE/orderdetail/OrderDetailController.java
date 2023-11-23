package fu.training.FrameMates_BE.orderdetail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @PostMapping("{orderDetailId}")
    public ResponseEntity createFeedback(
            Authentication authentication,
            @PathVariable int orderDetailId,
            @RequestBody OrderDetailModel model
    ){
        orderDetailService.createFeedBack(orderDetailId, authentication, model);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/current")
    public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByCurrentAccount(
            Authentication authentication
    ){
        return ResponseEntity.ok(orderDetailService.getOrderDetailByCurrentUser(authentication));
    }
    @GetMapping("/studio/{id}")
    public ResponseEntity<List<FeedbackModel>> getOrderDetailsByStudioId(
            @PathVariable int id
    ){
        return ResponseEntity.ok(orderDetailService.getOrderDetailByStudioId(id));
    }
}

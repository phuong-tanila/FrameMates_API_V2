package fu.training.FrameMates_BE.order;

import fu.training.FrameMates_BE.orderdetail.OrderDetailModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class OrderModel {
    private Integer orderId;

    private java.sql.Timestamp orderDate;

    private String status;

    private String description;

    private java.sql.Timestamp checkIn;

    private java.sql.Timestamp paymentDate;

    private Integer deposit;

    private String address;

    private Set<OrderDetailModel> orderDetails;

}

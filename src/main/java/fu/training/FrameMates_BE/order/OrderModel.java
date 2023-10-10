package fu.training.FrameMates_BE.order;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.orderdetail.OrderDetail;
import fu.training.FrameMates_BE.orderdetail.OrderDetailModel;
import fu.training.FrameMates_BE.studio.Studio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class OrderModel {
    private Integer orderId;

    private java.sql.Timestamp orderDate;

    private Integer status;

    private java.sql.Timestamp paymentDate;

    private Integer totalPrice;

    private int studioId;

    private int accountId;

    private Set<OrderDetailModel> orderDetails;

}

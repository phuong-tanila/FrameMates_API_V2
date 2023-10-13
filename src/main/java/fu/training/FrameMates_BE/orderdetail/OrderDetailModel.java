package fu.training.FrameMates_BE.orderdetail;

import fu.training.FrameMates_BE.slotbooking.SlotBookingModel;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrderDetailModel {

    private Integer orderDetailId;

    private Integer price;

    private Integer discount;

    private Integer rating;

    private String content;

    private int slotBookingId;

    private java.sql.Timestamp postDate;

    private java.sql.Timestamp startTime;

    private java.sql.Timestamp endTime;

}

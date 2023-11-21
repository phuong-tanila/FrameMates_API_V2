package fu.training.FrameMates_BE.orderdetail;

import fu.training.FrameMates_BE.slotbooking.SlotBookingModel;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalTime;

@Data
public class OrderDetailModel {

    private Integer orderDetailId;

    private Integer price;

    private Integer discount;

    private Integer rating;

    private String content;

    private int slotBookingId;

    private java.sql.Timestamp postDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private SlotBookingModel slotBooking;

}

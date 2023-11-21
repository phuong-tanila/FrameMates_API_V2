package fu.training.FrameMates_BE.orderdetail;

import lombok.Data;
import lombok.Setter;

import java.time.LocalTime;

@Data
public class FeedbackModel {
    private Integer orderDetailId;

    private Integer price;

    private Integer discount;

    private Integer rating;

    private String content;

    private int slotBookingId;

    private java.sql.Timestamp postDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String userName;

    private int userId;

    private String userAvatar;
}

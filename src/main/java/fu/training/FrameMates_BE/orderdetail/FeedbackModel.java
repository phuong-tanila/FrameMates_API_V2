package fu.training.FrameMates_BE.orderdetail;

import lombok.Data;
import lombok.Setter;

@Data
public class FeedbackModel {
    private Integer orderDetailId;

    private Integer price;

    private Integer discount;

    private Integer rating;

    private String content;

    private int slotBookingId;

    private java.sql.Timestamp postDate;

    private java.sql.Timestamp startTime;

    private java.sql.Timestamp endTime;

    private String userName;

    private int userId;

    private String userAvatar;
}

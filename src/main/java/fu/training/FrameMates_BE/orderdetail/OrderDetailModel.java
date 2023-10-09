package fu.training.FrameMates_BE.orderdetail;

import lombok.Data;

@Data
public class OrderDetailModel {

    private Integer orderDetailId;

    private Integer price;

    private Integer discount;

    private Integer rating;

    private String content;

    private java.sql.Timestamp postDate;

}

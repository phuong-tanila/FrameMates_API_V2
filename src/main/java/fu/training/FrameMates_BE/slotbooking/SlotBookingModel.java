package fu.training.FrameMates_BE.slotbooking;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SlotBookingModel {

    private Integer slotId;

    private java.sql.Timestamp startTime;

    private java.sql.Timestamp endTime;

    private Integer price;

    private Date SlotDate;

    private boolean booked;
}

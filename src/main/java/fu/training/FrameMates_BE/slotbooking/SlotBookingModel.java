package fu.training.FrameMates_BE.slotbooking;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class SlotBookingModel {

    private Integer slotId;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer price;

    private Date SlotDate;

    private boolean booked;

    private String bookingStatus;
}

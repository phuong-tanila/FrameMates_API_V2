package fu.training.FrameMates_BE.slotbooking;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/slot-bookings")
public class SlotBookingController {
    @Autowired
    private SlotBookingService slotBookingService;
    @Secured({"ROLE_OWNER"})
    @PostMapping
    public ResponseEntity createSlotBooking(
        @RequestBody SlotBookingModel model,
        Authentication authentication
    ) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/slot-bookings/" + slotBookingService.createSlotBooking(model, authentication))).build();

    }
    @GetMapping("{studioId}")
    public ResponseEntity<List<SlotBookingModel>> getSlotBooking(
            @PathVariable int studioId,
            @RequestParam(value = "date", required = false) String slotDate
    ) throws ParseException {
        Date parsedSlotDate = null;
        if(slotDate != null){
            parsedSlotDate = new SimpleDateFormat("yyyy-MM-dd").parse(slotDate);
            parsedSlotDate.setHours(0);
            parsedSlotDate.setMinutes(0);
            parsedSlotDate.setSeconds(0);
        }
        return ResponseEntity.ok(slotBookingService.getBookingSlotsByDate(studioId, parsedSlotDate));
    }


}

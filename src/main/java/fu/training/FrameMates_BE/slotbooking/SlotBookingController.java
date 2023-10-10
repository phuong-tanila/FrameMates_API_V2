package fu.training.FrameMates_BE.slotbooking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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



}

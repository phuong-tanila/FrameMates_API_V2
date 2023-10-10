package fu.training.FrameMates_BE.slotbooking;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SlotBookingService {
    @Autowired
    private SlotBookingRepository slotBookingRepository;

    @Autowired
    private SlotBookingMapper slotBookingMapper;
    public int createSlotBooking(SlotBookingModel model, Authentication authentication) {
        var currentAccount = (Account) authentication.getPrincipal();
        var currentStudio = currentAccount.getStudio();
        if(currentStudio == null) throw new RecordNotFoundException("You are not own a studio to do this feature");
        var entity = slotBookingMapper.fromCreateModelToEntity(model);
        entity.setStudio(currentStudio);
        return slotBookingRepository.save(entity).getSlotId();
    }
}

package fu.training.FrameMates_BE.slotbooking;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j
public class SlotBookingService {
    @Autowired
    private SlotBookingRepository slotBookingRepository;

    @Autowired
    private SlotBookingMapper slotBookingMapper;
    public int createSlotBooking(SlotBookingModel model, Authentication authentication) {
        var currentAccount = (Account) authentication.getPrincipal();
        var currentStudio = currentAccount.getStudio();
        var date = model.getSlotDate();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        if(currentStudio == null) throw new RecordNotFoundException("You are not own a studio to do this feature");
        var entity = slotBookingMapper.fromCreateModelToEntity(model);
        entity.setStudio(currentStudio);
        return slotBookingRepository.save(entity).getSlotId();
    }

    public List<SlotBookingModel> getBookingSlotsByDate(int studioId, java.util.Date slotDate) {
        List<SlotBooking> slotBooking;
        System.out.println(slotDate);
        if(slotDate != null)
            slotBooking = slotBookingRepository.findAllByStudio_StudioIdAndSlotDate(studioId, slotDate);
        else
            slotBooking = slotBookingRepository.findAllByStudio_StudioId(studioId);
        return slotBooking.stream().map(slotBookingMapper::toModel).toList();
    }
}

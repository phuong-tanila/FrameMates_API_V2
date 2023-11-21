package fu.training.FrameMates_BE.slotbooking;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.share.exceptions.MissingBearerTokenException;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
import java.util.ArrayList;
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
//        entity.setBookingStatus(SlotBookingStatus.AVAILABLE.ordinal());
        return slotBookingRepository.save(entity).getSlotId();
    }

    public List<SlotBookingModel> getBookingSlotsByDate(int studioId, java.util.Date slotDate) {
        List<SlotBooking> slotBooking;
        if(slotDate != null){
            setEndOfDay(slotDate);
            System.out.println(slotDate);
            slotBooking = slotBookingRepository.findAllByStudio_StudioIdAndSlotDate(studioId, slotDate);
//        return new ArrayList<>();
        }
        else
            slotBooking = slotBookingRepository.findAllByStudio_StudioId(studioId);
        return slotBooking.stream().map(slotBookingMapper::toModel).toList();
    }
    private void setEndOfDay(java.util.Date date){
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
    }
    public SlotBooking getSlotBookingEntityById(int slotBookingId) {
        return slotBookingRepository.findById(slotBookingId)
                .orElseThrow(() -> new RecordNotFoundException("Slot booking id: " + slotBookingId + " not found"));
    }

    public List<SlotBookingModel> getBookingSlotsByCurrentStudio(Authentication authentication) {
        if(authentication != null){
            var currentAccount = (Account) authentication.getPrincipal();
            var currentStudio = currentAccount.getStudio();
            return getBookingSlotsByDate(currentStudio.getStudioId(), null);
        }
        throw new MissingBearerTokenException();
    }
}

package fu.training.FrameMates_BE.slotbooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SlotBookingRepository extends JpaRepository<SlotBooking, Integer> {


    List<SlotBooking> findAllByStudio_StudioId(int studioId);
}

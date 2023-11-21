package fu.training.FrameMates_BE.slotbooking;

import jakarta.persistence.TemporalType;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SlotBookingRepository extends JpaRepository<SlotBooking, Integer> {
    @Query("select sl from SlotBooking sl where sl.studio.studioId = :studioId and cast(:slotDate as date) = cast(sl.slotDate as date) ")
    List<SlotBooking> findAllByStudio_StudioIdAndSlotDate(int studioId, Date slotDate);

    List<SlotBooking> findAllByStudio_StudioId(int studioId);
}

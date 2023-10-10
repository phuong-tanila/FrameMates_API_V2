package fu.training.FrameMates_BE.slotbooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotBookingRepository extends JpaRepository<SlotBooking, Integer> {

}

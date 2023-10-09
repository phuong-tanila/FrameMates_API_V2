package fu.training.FrameMates_BE.sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsOtpRepository extends JpaRepository<SmsOtp, Integer> {

}

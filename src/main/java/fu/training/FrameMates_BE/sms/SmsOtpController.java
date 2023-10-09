package fu.training.FrameMates_BE.sms;

import fu.training.FrameMates_BE.share.exceptions.InvalidOtpException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/otp")
public class SmsOtpController {
}

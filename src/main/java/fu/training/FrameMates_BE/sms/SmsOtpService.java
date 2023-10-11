package fu.training.FrameMates_BE.sms;


import com.twilio.Twilio;
import com.twilio.twiml.messaging.Body;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import fu.training.FrameMates_BE.share.configs.SmsConfig;
import fu.training.FrameMates_BE.share.exceptions.InvalidOtpException;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SmsOtpService {
    @Autowired
    private SmsOtpRepository smsOtpRepository;
    @Autowired
    private SmsOtpMapper mapper;
    @Autowired
    private SmsConfig smsConfig;
    private PhoneNumber fromPhoneNumber;
    @PostConstruct
    public void setUpTwilio(){
        Twilio.init(smsConfig.getSid(), smsConfig.getAuthToken());
        fromPhoneNumber = new PhoneNumber(smsConfig.getTwilioPhoneNumber());
    }


    public SmsOtpModel createOtp(String phoneNumber) {
        String otp = generateOTP();
        SmsOtp sms = new SmsOtp();
        String body = smsConfig.getOtpBodyTemplate() + otp;
        PhoneNumber toPhoneNumber = new PhoneNumber(phoneNumber);
        Message message = Message.creator(
                toPhoneNumber,
                fromPhoneNumber,
                body
        ).create();
        sms.setOtpValue(otp);
        sms.setCreateAt(new Timestamp(new Date().getTime()));
        sms.setExpiredAt(new Timestamp(new Date().getTime() + smsConfig.getOtpExpiration()));
        sms.setPhoneNumber(phoneNumber);
        sms = smsOtpRepository.save(sms);
        sms.setOtpValue(null);
        return mapper.toModel(sms);
    }

    public String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

    private SmsOtp findOtp(int otpId, String phoneNumber, String otpValue) {

        Optional<SmsOtp> optionalSmsOtp = smsOtpRepository
                .findByOtpIdAndPhoneNumberAndOtpValue(otpId, phoneNumber, otpValue);
        if(optionalSmsOtp.isEmpty())
            throw new InvalidOtpException("Sms otp invalid!!");
        return optionalSmsOtp.get();
    }
    public boolean verifyOTP(int otpId, String phoneNumber, String otpValue) {
        SmsOtp smsOtp = findOtp(otpId, phoneNumber, otpValue);
        Timestamp currentTimeStamp = new Timestamp(new Date().getTime());
        if(smsOtp.getExpiredAt().before(currentTimeStamp))
            throw new InvalidOtpException("Sms otp expired");
        return true;
    }
}

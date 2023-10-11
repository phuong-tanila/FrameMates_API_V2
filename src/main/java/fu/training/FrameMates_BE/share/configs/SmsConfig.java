package fu.training.FrameMates_BE.share.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class SmsConfig {
    @Value("${sms.otp-expiration}")
    private long otpExpiration;
    @Value("${sms.otp-body-template}")
    private String otpBodyTemplate;
    @Value("${sms.eSms.sendSmsApiUrl}")
    private String eSmsSendSmsApiUrl;
    @Value("${sms.eSms.apiKey}")
    private String eSmsApiKey;
    @Value("${sms.eSms.secret}")
    private String eSmsSecret;

    @Value("${sms.twilio.sid}")
    private String sid;

    @Value("${sms.twilio.auth-token}")
    private String authToken;

    @Value("${sms.twilio.phone-number}")
    private String twilioPhoneNumber;
}

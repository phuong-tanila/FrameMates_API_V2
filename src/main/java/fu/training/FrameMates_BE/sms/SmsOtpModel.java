package fu.training.FrameMates_BE.sms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SmsOtpModel {
    @NotNull(message = "Otp id not specified!")
    private Integer otpId;

    private java.sql.Timestamp createAt;

    private java.sql.Timestamp expiredAt;

    @NotBlank(message = "Phone number not specified!")
    private String phoneNumber;
    @NotBlank(message = "Otp value not specified!")
    private String otpValue;
}

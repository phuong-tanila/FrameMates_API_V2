package fu.training.FrameMates_BE.sms;


import fu.training.FrameMates_BE.share.exceptions.InvalidOtpException;

public interface SmsOtpService {
    SmsOtpModel sendOtp(String phoneNumber);

    String generateOTP();

    boolean verifyOTP(int otpId, String phoneNumber, String otpValue)throws InvalidOtpException;
}

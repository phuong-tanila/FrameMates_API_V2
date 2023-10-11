package fu.training.FrameMates_BE.sms;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
@JsonSerialize
@Entity
@Table(name="sms_otp")
public class SmsOtp implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="otp_id")
	private Integer otpId;
	
	@Column(name="create_at")
	private java.sql.Timestamp createAt;
	
	@Column(name="expired_at")
	private java.sql.Timestamp expiredAt;
	
	@Column(name="otp_value")
	private String otpValue;
	
	@Column(name="phone_number")
	private String phoneNumber;
}

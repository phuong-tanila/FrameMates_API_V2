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
	private Integer otp_id;
	
	@Column(name="create_at")
	private java.sql.Timestamp create_at;
	
	@Column(name="expired_at")
	private java.sql.Timestamp expired_at;
	
	@Column(name="otp_value")
	private String otp_value;
	
	@Column(name="phone_number")
	private String phone_number;
}

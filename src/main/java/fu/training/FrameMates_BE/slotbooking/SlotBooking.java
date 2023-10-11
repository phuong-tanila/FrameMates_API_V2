package fu.training.FrameMates_BE.slotbooking;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.orderdetail.OrderDetail;
import fu.training.FrameMates_BE.share.entities.SoftDeleteEntity;
import fu.training.FrameMates_BE.studio.Studio;
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
@Table(name="slot_booking")
public class SlotBooking extends SoftDeleteEntity implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slot_id")
	private Integer slotId;
	
	@Column(name="start_time")
	private java.sql.Timestamp startTime;
	
	@Column(name="end_time")
	private java.sql.Timestamp endTime;
	
	@Column(name="price")
	private Integer price;

	@Column(name="booked")
	private boolean booked;

	@Column(name="monday")
	private boolean monday;

	@Column(name="tuesday")
	private boolean tuesday;

	@Column(name="wednesday")
	private boolean wednesday;

	@Column(name="thursday")
	private boolean thursday;

	@Column(name="friday")
	private boolean friday;

	@Column(name="saturday")
	private boolean saturday;

	@Column(name="sunday")
	private boolean sunday;



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slotBooking", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;


	@ManyToOne(targetEntity= Studio.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="studio_id", referencedColumnName="studio_id") })
	private Studio studio;
}

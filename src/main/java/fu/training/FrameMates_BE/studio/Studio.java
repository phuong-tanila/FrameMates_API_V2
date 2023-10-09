package fu.training.FrameMates_BE.studio;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.mediafile.MediaFile;
import fu.training.FrameMates_BE.order.Order;
import fu.training.FrameMates_BE.slotbooking.SlotBooking;
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
@Table(name="studio")
public class Studio implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="studio_id")
	private Integer studio_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="avatar_studio", length = Integer.MAX_VALUE)
	private String avatar_studio;
	
	@Column(name="cover_image", length = Integer.MAX_VALUE)
	private String cover_image;
	
	@Column(name="address", length = Integer.MAX_VALUE)
	private String address;
	
	@Column(name="description", length = Integer.MAX_VALUE)
	private String description;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="total_rating")
	private Float total_rating;
	
	@ManyToOne(targetEntity= Account.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="owner", referencedColumnName="account_id") })
	private Account owner;
	
	@OneToMany(mappedBy="studio", targetEntity= MediaFile.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<MediaFile> ORM_media_file;
	
	@OneToMany(mappedBy="studio", targetEntity= Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Order> ORM_order;
	
	@OneToMany(mappedBy="studio", targetEntity= SlotBooking.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SlotBooking> ORM_slot_booking;
}

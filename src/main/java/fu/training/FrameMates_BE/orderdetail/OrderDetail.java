package fu.training.FrameMates_BE.orderdetail;
import java.io.Serializable;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.order.Order;
import fu.training.FrameMates_BE.slotbooking.SlotBooking;
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
@Table(name="order_detail")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	private Integer orderDetailId;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	@Column(name="end_time")
	private LocalTime endTime;
	
	@Column(name="content", length=Integer.MAX_VALUE)
	private String content;
	
	@Column(name="rating")
	private Integer rating;
	
	@Column(name="post_date")
	private java.sql.Timestamp postDate;

	@ManyToOne(targetEntity= SlotBooking.class, cascade = CascadeType.ALL)
	private SlotBooking slotBooking;

	@ManyToOne(targetEntity=Order.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumns(value={ @JoinColumn(name="order_id", referencedColumnName="order_id")})
	private Order order;
}

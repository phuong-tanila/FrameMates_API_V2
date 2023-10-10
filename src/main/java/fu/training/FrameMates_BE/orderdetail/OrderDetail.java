package fu.training.FrameMates_BE.orderdetail;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.order.Order;
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
	private java.sql.Timestamp startTime;
	
	@Column(name="end_time")
	private java.sql.Timestamp endTime;
	
	@Column(name="content", length=Integer.MAX_VALUE)
	private String content;
	
	@Column(name="rating")
	private Integer rating;
	
	@Column(name="post_date")
	private java.sql.Timestamp postDate;
	
	@ManyToOne(targetEntity=Order.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="order_id", referencedColumnName="order_id") })
	private Order order;
}

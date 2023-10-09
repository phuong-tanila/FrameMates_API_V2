package fu.training.FrameMates_BE.order;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.orderdetail.OrderDetail;
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
@Table(name="`order`")
public class Order implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer order_id;
	
	@Column(name="order_date")
	private java.sql.Timestamp order_date;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="payment_date")
	private java.sql.Timestamp payment_date;
	
	@Column(name="total_price")
	private Integer total_price;
	
	@ManyToOne(targetEntity= Studio.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="studio_id", referencedColumnName="studio_id") })
	private Studio studio;
	
	@ManyToOne(targetEntity= Account.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="account_id", referencedColumnName="account_id") })
	private Account account;
	
	@OneToMany(mappedBy="order", targetEntity= OrderDetail.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderDetail> ORM_order_detail;

}

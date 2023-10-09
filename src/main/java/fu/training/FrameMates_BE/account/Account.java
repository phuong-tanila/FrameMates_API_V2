package fu.training.FrameMates_BE.account;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.order.Order;
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
@Table(name="account")
public class Account implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private Integer account_id;
	
	@Column(name="avatar", length=Integer.MAX_VALUE)
	private String avatar;
	
	@Column(name="email", length=Integer.MAX_VALUE)
	private String email;
	
	@Column(name="full_name", length=Integer.MAX_VALUE)
	private String full_name;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="create_date")
	private java.sql.Timestamp create_date;
	
	@ManyToOne(targetEntity=Account.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="admin_id", referencedColumnName="account_id", nullable=false) })
	private Account admin;
	
	@OneToMany(mappedBy="owner", targetEntity= Studio.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Studio> ORM_studio;
	
	@OneToMany(mappedBy="account", targetEntity= Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Order> ORM_order;
	
	@OneToMany(mappedBy="admin", targetEntity=Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> ORM_account;

}

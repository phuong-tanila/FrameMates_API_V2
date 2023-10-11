package fu.training.FrameMates_BE.account;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.order.Order;
import fu.training.FrameMates_BE.share.entities.SoftDeleteEntity;
import fu.training.FrameMates_BE.studio.Studio;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@JsonSerialize
@Entity
@Table(name="account")
public class Account extends SoftDeleteEntity implements Serializable, UserDetails {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private Integer accountId;
	
	@Column(name="avatar", length=Integer.MAX_VALUE)
	private String avatar;
	
	@Column(name="email", length=Integer.MAX_VALUE)
	private String email;
	
	@Column(name="full_name", length=Integer.MAX_VALUE)
	private String fullName;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="create_date")
	private java.sql.Timestamp createDate;
	
	@ManyToOne(targetEntity=Account.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="admin_id", referencedColumnName="account_id", nullable=true) })
	private Account admin;
	
	@OneToOne(mappedBy="owner", targetEntity= Studio.class, cascade = CascadeType.ALL)
	private Studio studio;
	
	@OneToMany(mappedBy="account", targetEntity= Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Order> orders;
	
	@OneToMany(mappedBy="admin", targetEntity=Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> accounts;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(this.getRole()));
	}

	@Override
	public String getUsername() {
		return phone;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountId=" + accountId +
				", avatar='" + avatar + '\'' +
				", email='" + email + '\'' +
				", full_name='" + fullName + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				", status=" + status +
				", createDate=" + createDate +
				", deleted=" + deleted +
				'}';
	}

	@Override
	public boolean isEnabled() {
		return !deleted;
	}

}

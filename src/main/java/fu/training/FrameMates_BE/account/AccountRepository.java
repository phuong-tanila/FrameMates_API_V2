package fu.training.FrameMates_BE.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.phone = :credential OR a.email = :credential")
    Account findByPhoneOrEmail(String credential);

    @Query("SELECT a FROM Account a WHERE a.phone = :phone")
    List<Account> findAllByPhone(String phone);

    List<Account> findAllByEmailOrPhone(String email, String phone);
}

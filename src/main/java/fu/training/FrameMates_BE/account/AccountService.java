package fu.training.FrameMates_BE.account;


import fu.training.FrameMates_BE.share.exceptions.DupplicatedUserInfoException;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AccountService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


    List<Account> findAccountsByUsernameOrPhone(String username, String phone);

    List<Account> findAll();

    Account createAccount(Account account);

    Account findAccountByEmployeeId(int employeeId) throws RecordNotFoundException;
    Account findAccountByCustomerId(int customerId) throws RecordNotFoundException;

    void validateAccount(Account mappedAccount) throws DupplicatedUserInfoException;
}

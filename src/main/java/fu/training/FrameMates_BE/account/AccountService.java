package fu.training.FrameMates_BE.account;


import fu.training.FrameMates_BE.share.exceptions.DupplicatedUserInfoException;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByPhoneOrEmail(username);
        if(account == null) throw new UsernameNotFoundException("Username or phone not found: " + username);
        return account;
    }

    public List<Account> findAccountsByPhone(String phone) {
        return accountRepository.findAllByPhone(phone);
    }


    public List<Account> findAll() {
        return  accountRepository.findAll();
    }
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }



    public void validateAccount(Account mappedAccount) {
        List<Account> accountList = findAccountsByPhone(mappedAccount.getPhone());
        if(accountList != null && !accountList.isEmpty()) {
            Set<String> errorMessages = new HashSet<>();
            for (Account account: accountList) {
                if(account.getPhone().equals(mappedAccount.getPhone())){
                    errorMessages.add("phone: phone is existed in our system");
                }
                if(account.getEmail().equals(mappedAccount.getEmail())){
                    errorMessages.add("email: email is existed in our system");
                }
            }
            throw new DupplicatedUserInfoException(errorMessages.toArray(new String[0]));
        }
    }
    private void createAccount(AccountModel accountModel){
        accountModel.setPassword(passwordEncoder.encode(accountModel.getPassword()));
        var account = accountMapper.fromCreateModelToEntity(accountModel);
        accountRepository.save(account);
    }
    public void createCustomer(AccountModel customer) {
        customer.setRole("CUS");
        createAccount(customer);
    }


}

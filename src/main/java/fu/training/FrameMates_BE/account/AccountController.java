package fu.training.FrameMates_BE.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import fu.training.FrameMates_BE.share.exceptions.DupplicatedUserInfoException;
import fu.training.FrameMates_BE.share.exceptions.MissingBearerTokenException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest request){
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCredential(),
                        request.getPassword()
                )
        );
        String accessToken = jwtService.generateToken(TokenType.ACCESSTOKEN, (Account) authentication.getPrincipal());
        String refreshToken = jwtService.generateToken(TokenType.REFRESHTOKEN, (Account) authentication.getPrincipal());
        return new ResponseEntity<TokenResponse>(new TokenResponse(accessToken, refreshToken), HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity createCustomer(@RequestBody @Valid AccountModel customer) throws JsonProcessingException, DupplicatedUserInfoException {
        accountService.createCustomer(customer);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PostMapping("/confirm-password")
    public ResponseEntity create(
            @RequestBody String password,
            Authentication authentication
    ){
        if(authentication == null) throw new MissingBearerTokenException();
        var currentAccount = (Account) authentication.getPrincipal();
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        currentAccount.getUsername(),
                        password
                )
        );
        return ResponseEntity.ok().build();
    }
    @GetMapping("/current")
    public ResponseEntity getCurrentAccount(
            Authentication authentication
    ){
        return ResponseEntity.ok(accountService.getCurrentAccount(authentication));
    }
}

package fu.training.FrameMates_BE.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginRequest {
    @NotNull(message = "You must enter phone or email")
    @Size(min = 5, max = 20)
    @NotBlank
    private String credential;
    @Size(min = 8, max = 20)
    @NotBlank
    private String password;
}

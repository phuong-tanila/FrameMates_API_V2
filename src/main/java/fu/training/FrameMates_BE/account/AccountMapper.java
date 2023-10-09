package fu.training.FrameMates_BE.account;

import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AccountMapper {


    //    @Mapping(source = "employee", target = "employee")
//    @Mapping(source = "customer", target = "customer")
//    @Mapping(source = "administrator", target = "administrator")
    @Mapping(source = "accountId", target = "accountId", ignore = true)
    public abstract Account fromCreateModelToEntity (AccountModel model);
    @Mapping(source = "password", target = "password", ignore = true)
    public abstract AccountModel toModel (Account entity);



}
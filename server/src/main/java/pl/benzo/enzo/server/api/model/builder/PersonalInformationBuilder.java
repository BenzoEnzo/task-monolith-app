package pl.benzo.enzo.server.api.model.builder;


import lombok.Builder;
import lombok.Data;
import lombok.With;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;

@Builder
@Data
@With
public class PersonalInformationBuilder {
    private AccountDto accountDto;
    private UserDto userDto;
}

package pl.benzo.enzo.server.api.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.util.Role;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountDto {
    private Long id;
    private String mail;
    private BigDecimal money = BigDecimal.valueOf(0);
    private Role role = Role.USER;
    private String password;
}

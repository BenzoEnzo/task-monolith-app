package pl.benzo.enzo.server.api.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.benzo.enzo.server.util.enumeration.Role;

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
    private String photoId;
    private boolean enabled;
}

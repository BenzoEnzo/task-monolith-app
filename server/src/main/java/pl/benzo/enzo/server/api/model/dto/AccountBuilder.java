package pl.benzo.enzo.server.api.model.dto;


import lombok.Builder;
import lombok.Data;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.util.Role;

import java.math.BigDecimal;

@Builder
@Data
public class AccountBuilder {
    private Long id;
    private String mail;
    private final BigDecimal money = BigDecimal.valueOf(0);
    private final Role role = Role.USER;
    private UserEntity userRelation;
    private String password;
}

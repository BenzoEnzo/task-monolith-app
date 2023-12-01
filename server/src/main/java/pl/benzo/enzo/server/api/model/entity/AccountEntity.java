package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.benzo.enzo.server.util.Role;

import java.math.BigDecimal;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "ACCOUNTS")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private UserEntity userRelation;
    private String mail;
    private String password;
    private BigDecimal money;
    private Role role;
}

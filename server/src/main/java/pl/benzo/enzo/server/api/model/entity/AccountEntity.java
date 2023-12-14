package pl.benzo.enzo.server.api.model.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.benzo.enzo.server.util.enumeration.Role;

import java.math.BigDecimal;
import java.util.Random;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNTS")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Nonnull
    @Column(unique = true)
    private String mail;
    @Nonnull
    private String password;
    private BigDecimal money = BigDecimal.ZERO;
    private Role role = Role.USER;
    private String photoId = String.valueOf(randomIDGen());
    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    private Long randomIDGen(){
        return new Random().nextLong(123456789L);
    }
}
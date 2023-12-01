package pl.benzo.enzo.server.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    AccountEntity findAccountEntityByMail(String mail);
    AccountEntity findAccountEntityByMailAndPassword(String mail, String password);
}

package pl.benzo.enzo.server.api.service.logic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.AccountBuilder;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.repository.AccountRepository;
import pl.benzo.enzo.server.util.Role;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public AccountBuilder create(AccountBuilder accountBuilder) {
        final AccountEntity acc = accountRepository.findAccountEntityByMail(accountBuilder.getMail());

        if(acc != null){
            throw new IllegalArgumentException("Konto o podanym mailu już istnieje");
        } else {
            final AccountEntity account = new AccountEntity();
            account.setMail(accountBuilder.getMail());
            account.setPassword(accountBuilder.getPassword());
            accountRepository.save(account);
            return AccountBuilder.builder()
                    .mail(accountBuilder.getMail())
                    .build();
        }
    }

    @Override
    public AccountBuilder loggIn(AccountBuilder accountBuilder) {
        final AccountEntity acc = accountRepository.findAccountEntityByMailAndPassword(accountBuilder.getMail(), accountBuilder.getPassword());

        if(acc == null){
            throw new IllegalArgumentException("Nie znaleziono danych użytkownika");
        } else {
            return AccountBuilder
                    .builder()
                    .id(acc.getId())
                    .build();
        }
    }

    @Override
    public AccountBuilder update(AccountBuilder accountBuilder) {
        final AccountEntity acc = accountRepository.findById(accountBuilder.getId())
                .orElseThrow(() -> new IllegalArgumentException("Uzytkownik o takim ID nie istnieje"));

        Optional.ofNullable(accountBuilder.getRole()).ifPresent(acc::setRole);
        Optional.ofNullable(accountBuilder.getMoney()).ifPresent(acc::setMoney);
        Optional.ofNullable(accountBuilder.getPassword()).ifPresent(acc::setPassword);
        Optional.ofNullable(accountBuilder.getUserRelation()).ifPresent(acc::setUserRelation);

        accountRepository.save(acc);

        return accountBuilder;
    }
}
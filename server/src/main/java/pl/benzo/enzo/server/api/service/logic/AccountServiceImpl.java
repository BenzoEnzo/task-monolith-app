package pl.benzo.enzo.server.api.service.logic;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.repository.AccountRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public void create(AccountDto accountDto) {
        final Optional<AccountEntity> acc = accountRepository.findAccountEntityByMail(accountDto.getMail());

        if(acc.isPresent()){
            throw new IllegalArgumentException("Konto o podanym mailu już istnieje");
        } else {
            final AccountEntity account = new AccountEntity();
            final UserEntity user = new UserEntity();
            account.setMail(accountDto.getMail());
            account.setPassword(accountDto.getPassword());
            account.setUser(user);
            accountRepository.save(account);
        }
    }

    @Override
    public AccountDto loggIn(AccountDto accountDto) {
        final AccountEntity acc = accountRepository.findAccountEntityByMailAndPassword(accountDto.getMail(), accountDto.getPassword());

        if(acc == null){
            throw new IllegalArgumentException("Nie znaleziono danych użytkownika");
        } else {
            AccountDto accDto = new AccountDto();
            accDto.setMail(acc.getMail());
            accDto.setId(acc.getId());
            accDto.setMoney(acc.getMoney());
            accDto.setPhotoId(acc.getPhotoId());
            return accDto;
        }
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        final AccountEntity acc = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Uzytkownik o takim ID nie istnieje"));

        Optional.ofNullable(accountDto.getRole()).ifPresent(acc::setRole);
        Optional.ofNullable(accountDto.getMoney()).ifPresent(acc::setMoney);
        Optional.ofNullable(accountDto.getPassword()).ifPresent(acc::setPassword);


        accountRepository.save(acc);

        return accountDto;
    }
}

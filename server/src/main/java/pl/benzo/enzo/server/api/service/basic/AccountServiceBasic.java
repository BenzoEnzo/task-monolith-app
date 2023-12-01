package pl.benzo.enzo.server.api.service.basic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceBasic {
    private final AccountRepository accountRepository;
    public AccountDto findAccountByMail(String mail){
        final AccountEntity accountEntity = accountRepository
                .findAccountEntityByMail(mail)
                .orElseThrow(() -> new IllegalArgumentException("NULLABLE ACCOUNT"));
        final AccountDto accountDto = new AccountDto();
        accountDto.setMail(accountEntity.getMail());
        return accountDto;
    }

    public AccountEntity findAccountById(Long id){
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NULLABLE ACCOUNT"));
    }
}

package pl.benzo.enzo.server.api.service.detail;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountDetailService {
    private final AccountRepository accountRepository;

    public String findAccountByMail(String mail){
        return accountRepository.findAccountEntityByMail(mail).getMail();
    }
}

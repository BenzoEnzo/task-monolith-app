package pl.benzo.enzo.server.api.service.logic.implementation;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.builder.PersonalInformationBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.model.entity.LinkEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.repository.AccountRepository;
import pl.benzo.enzo.server.api.service.basic.AccountServiceBasic;
import pl.benzo.enzo.server.api.service.basic.LinkServiceBasic;
import pl.benzo.enzo.server.api.service.logic.AccountService;
import pl.benzo.enzo.server.exception.account.AccountNotAllowedException;
import pl.benzo.enzo.server.exception.account.MailAlreadyExistException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final LinkServiceBasic linkServiceBasic;
    private final EmailServiceImpl emailServiceImpl;
    private final AccountServiceBasic accountServiceBasic;
    @Value("${app.account.confirmation.api}")
    private String confirmationAddress;
    @Override
    @Transactional
    public void create(AccountDto accountDto) {
        final Optional<AccountEntity> acc = accountRepository.findAccountEntityByMail(accountDto.getMail());

        if(acc.isPresent()){
            throw new MailAlreadyExistException("Mail: " + accountDto.getMail() + " jest już użytkowany");
        } else {
            final AccountEntity account = new AccountEntity();
            final UserEntity user = new UserEntity();
            account.setMail(accountDto.getMail());
            account.setPassword(accountDto.getPassword());
            account.setUser(user);
            account.setEnabled(false);
            accountRepository.save(account);

            final LinkEntity linkEntity = new LinkEntity();
            linkEntity.setAccount(account);
            linkServiceBasic.createLinkForAccount(linkEntity);
            final String link = confirmationAddress + linkEntity.getGeneratedVal();
            final String htmlBody = "<h1>Witaj!</h1><p>Kliknij <a href='" + link + "'>tutaj</a> aby aktywować swoje konto.</p>";
            emailServiceImpl.sendEmail(accountDto.getMail(), "Welcome: Area Account Confirmation", htmlBody);
        }
    }

    @Override
    public AccountDto loggIn(AccountDto accountDto) {
        final AccountEntity acc = accountRepository.findAccountEntityByMailAndPasswordAndEnabled
                (accountDto.getMail(), accountDto.getPassword(), false); //TODO must be true, for testing = false
        if(acc == null){
            throw new AccountNotAllowedException("Wprowadzono błędne hasło dla: " + accountDto.getMail());
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

    @Override
    public PersonalInformationBuilder getInformationAboutMe(AccountDto accountDto){
        return accountServiceBasic.getInformationAboutMe(accountDto.getId());
    }
}

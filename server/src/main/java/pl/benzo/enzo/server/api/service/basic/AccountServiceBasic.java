package pl.benzo.enzo.server.api.service.basic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.builder.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceBasic {
    private final AccountRepository accountRepository;
    private final UserServiceBasic userServiceBasic;
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

    public EntitiesBuilder getInformationAboutMe(Long id){
        final AccountEntity accountEntity = findAccountById(id);
        final AccountDto accountDto = new AccountDto();
        accountDto.setMail(accountEntity.getMail());
        accountDto.setMoney(accountEntity.getMoney());
        accountDto.setRole(accountEntity.getRole());
        final UserEntity userEntity = userServiceBasic.findUserById(id);
        final UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());

        return EntitiesBuilder.builder()
                .accountDto(accountDto)
                .userDto(userDto)
                .build();
    }
}

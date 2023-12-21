package pl.benzo.enzo.server.api.service.basic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.builder.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.builder.PersonalInformationBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.repository.AccountRepository;
import pl.benzo.enzo.server.exception.user.UserAuthorizationException;

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
                .orElseThrow(() -> new UserAuthorizationException("Zostałeś wylogowany ! Zaloguj się ponownie"));
    }

    public PersonalInformationBuilder getInformationAboutMe(Long id){
        final AccountEntity accountEntity = findAccountById(id);
        final UserEntity userEntity = accountEntity.getUser();
        final AccountDto accountDto = new AccountDto();
        final UserDto userDto = new UserDto();

        accountDto.setMail(accountEntity.getMail());
        accountDto.setMoney(accountEntity.getMoney());
        accountDto.setRole(accountEntity.getRole());
        accountDto.setPhotoId(accountDto.getPhotoId());

        userDto.setName(userEntity.getName());
        userDto.setScore(userEntity.getScore());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPesel(userEntity.getPesel());
        userDto.setPhone(userEntity.getPhone());

        return PersonalInformationBuilder.builder()
                .accountDto(accountDto)
                .userDto(userDto)
                .build();
    }

    public void update(AccountEntity accountEntity){
        accountRepository.save(accountEntity);
    }
}

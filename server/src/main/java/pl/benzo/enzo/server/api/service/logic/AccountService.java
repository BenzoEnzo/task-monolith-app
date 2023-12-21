package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.builder.PersonalInformationBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;

public interface AccountService {
    void create(AccountDto accountDto);
    AccountDto loggIn(AccountDto accountDto);

    AccountDto update(AccountDto accountDto);

    PersonalInformationBuilder getInformationAboutMe(AccountDto accountDto);
}

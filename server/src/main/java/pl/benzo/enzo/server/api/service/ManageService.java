package pl.benzo.enzo.server.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.builder.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.service.basic.AccountServiceBasic;

@Service
@RequiredArgsConstructor
public class ManageService {
    private final AccountServiceBasic accountServiceBasic;

    public EntitiesBuilder getInformationAboutMe(AccountDto accountDto){
        return accountServiceBasic.getInformationAboutMe(accountDto.getId());
    }
}

package pl.benzo.enzo.server.api.service;


import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.logic.AccountService;
import pl.benzo.enzo.server.api.service.logic.UserService;

import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceWithException {
    private final static Logger logger = LoggerFactory.getLogger(ServiceWithException.class);
    private final UserService userService;
    private final AccountService accountService;

    public Try<List<UserDto>> findAllUsers() {
        return Try.of(userService::findAll)
                .onFailure(ex -> logger.info(String.valueOf(ex)));
    }

    public void registration(AccountDto accountDto){
        accountService.create(accountDto);
    }
    public AccountDto loggIn(AccountDto accountDto){
        return accountService.loggIn(accountDto);
    }

    public UserDto createUser(EntitiesBuilder entitiesBuilder){
        return userService.create(entitiesBuilder);
    }

}

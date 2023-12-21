package pl.benzo.enzo.server.api.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.benzo.enzo.server.api.model.builder.PersonalInformationBuilder;
import pl.benzo.enzo.server.api.model.builder.SuccessResponseBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.logic.*;
import pl.benzo.enzo.server.security.Jwt;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuccessService {
    private final AccountService accountService;
    private final EmailService emailService;
    private final NotificationService notificationService;
    private final TaskService taskService;
    private final UploaderService uploaderService;
    private final UserService userService;
    private final Jwt jwt;

    @ResponseBody
    public ResponseEntity<SuccessResponseBuilder> loggIn(AccountDto accountDto){
        final AccountDto accountDto1 = accountService.loggIn(accountDto);

        final String token = jwt.generateToken(accountDto.getMail());
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + token);

        final SuccessResponseBuilder response = SuccessResponseBuilder.builder()
                .httpStatus(HttpStatus.OK)
                .msg("Zalogowano pomyślnie !")
                .body(accountDto1)
                .build();

        return new ResponseEntity<>(Objects.requireNonNull(response), headers, HttpStatus.OK);
    }

    @ResponseBody
    public ResponseEntity<SuccessResponseBuilder> createAccount(AccountDto accountDto){
        accountService.create(accountDto);

        final SuccessResponseBuilder response = SuccessResponseBuilder.builder()
                .httpStatus(HttpStatus.CREATED)
                .msg("Zarejestrowano pomyślnie ! Konto należy potwierdzić na adresie mailowym")
                .build();

        return new ResponseEntity<>(Objects.requireNonNull(response), HttpStatus.CREATED);
    }

    @ResponseBody
    public ResponseEntity<PersonalInformationBuilder> userAuthorized(AccountDto accountDto){
        final PersonalInformationBuilder response = accountService.getInformationAboutMe(accountDto);
        return new ResponseEntity<>(Objects.requireNonNull(response), HttpStatus.OK);
    }

    @ResponseBody
    public ResponseEntity<SuccessResponseBuilder> editAccountData(UserDto userDto){
        userService.update(userDto);

        final SuccessResponseBuilder response = SuccessResponseBuilder.builder()
                .httpStatus(HttpStatus.CREATED)
                .msg("Dane zostały zaaktualizowane !")
                .build();

        return new ResponseEntity<>(Objects.requireNonNull(response), HttpStatus.CREATED);
    }
}

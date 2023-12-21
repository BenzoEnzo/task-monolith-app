package pl.benzo.enzo.server.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.benzo.enzo.server.api.model.builder.SuccessResponseBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.service.logic.AccountService;
import pl.benzo.enzo.server.security.JWT;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuccessService {
    private final AccountService accountService;
    private final JWT jwt;

    @ResponseBody
    public ResponseEntity<SuccessResponseBuilder> loggIn(AccountDto accountDto){
        final AccountDto accountDto1 = accountService.loggIn(accountDto);

        final String token = jwt.generateToken(accountDto.getMail());
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + token);

        final SuccessResponseBuilder response = SuccessResponseBuilder.builder()
                .httpStatus(HttpStatus.OK)
                .msg("Zalogowano pomyślnie !")
                .body(accountDto1).build();

        return new ResponseEntity<>(Objects.requireNonNull(response), headers, HttpStatus.OK);
    }
}

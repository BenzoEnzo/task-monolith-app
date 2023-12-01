package pl.benzo.enzo.server.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.service.ServiceWithException;
import pl.benzo.enzo.server.security.JWT;

import java.util.Objects;

@RestController
@RequestMapping("/api/unauthorized")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UnauthorizedController {
    private final ServiceWithException service;
    private final JWT jwt;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUpUser(@RequestBody AccountDto accountDto){
        service.registration(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> signInUser(@RequestBody AccountDto accountDto) {
        final AccountDto response = service.loggIn(accountDto);

        if(response != null){
            final String token = jwt.generateToken(accountDto.getMail());
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer " + token);
            return new ResponseEntity<>(Objects.requireNonNull(response), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
          }
        }

}



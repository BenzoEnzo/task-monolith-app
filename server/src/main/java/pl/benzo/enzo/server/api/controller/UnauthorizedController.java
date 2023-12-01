package pl.benzo.enzo.server.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.AccountBuilder;
import pl.benzo.enzo.server.api.service.ServiceWithException;
import pl.benzo.enzo.server.security.JWT;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/unauthorized")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UnauthorizedController {
    private final ServiceWithException service;
    private final JWT jwt;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUpUser(@RequestBody AccountBuilder accountBuilder){
        return ResponseEntity.ok().body(service.registration(accountBuilder));
    }
    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> signInUser(@RequestBody AccountBuilder accountBuilder) {
        final AccountBuilder response = service.loggIn(accountBuilder);

        if(response != null){
            final String token = jwt.generateToken(accountBuilder.getMail());
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer " + token);
            return new ResponseEntity<>(Objects.requireNonNull(response), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
          }
        }

}



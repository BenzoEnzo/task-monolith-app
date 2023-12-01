package pl.benzo.enzo.server.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.benzo.enzo.server.api.model.dto.AccountBuilder;
import pl.benzo.enzo.server.api.service.ServiceWithException;

@RestController
@RequestMapping("/api/unauthorized")
@RequiredArgsConstructor
public class UnauthorizedController {
    private final ServiceWithException service;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUpUser(@RequestBody AccountBuilder accountBuilder){
        return ResponseEntity.ok().body(service.registration(accountBuilder));
    }
    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> signInUser(@RequestBody AccountBuilder accountBuilder){
            return ResponseEntity.ok().body(service.loggIn(accountBuilder));
        }
    }


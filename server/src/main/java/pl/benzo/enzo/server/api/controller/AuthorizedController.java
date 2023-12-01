package pl.benzo.enzo.server.api.controller;



import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.benzo.enzo.server.api.model.dto.UserBuilder;
import pl.benzo.enzo.server.api.service.ServiceWithException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorized")
public class AuthorizedController {
    private final ServiceWithException service;
    @GetMapping(value = "/que-users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> readUsers(){
        Try<List<UserBuilder>> response = service.findAllUsers();

        return response.isSuccess()
                ? ResponseEntity.ok(response.get())
                : ResponseEntity.internalServerError().body(response.getCause().getMessage());
    }
}

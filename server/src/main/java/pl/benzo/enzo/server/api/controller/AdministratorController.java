package pl.benzo.enzo.server.api.controller;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.ServiceWithException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/administrator")
@CrossOrigin("http://localhost:3000")
public class AdministratorController {
    private final ServiceWithException service;
    @GetMapping(value = "/que-users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> readUsers(){
        Try<List<UserDto>> response = service.findAllUsers();

        return response.isSuccess()
                ? ResponseEntity.ok(response.get())
                : ResponseEntity.internalServerError().body(response.getCause().getMessage());
    }
}

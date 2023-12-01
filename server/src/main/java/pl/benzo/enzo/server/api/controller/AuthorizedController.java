package pl.benzo.enzo.server.api.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.EntitiesBuilder;
import pl.benzo.enzo.server.api.service.ServiceWithException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorized")
@CrossOrigin("http://localhost:3000")
public class AuthorizedController {
    private final ServiceWithException service;
    @PostMapping(value = "/user-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> creeateUserData(@RequestBody EntitiesBuilder entitiesBuilder){
        return ResponseEntity.ok().body(service.createUser(entitiesBuilder));
    }
}

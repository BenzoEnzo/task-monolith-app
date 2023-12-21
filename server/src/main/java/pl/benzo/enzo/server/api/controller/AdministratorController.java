package pl.benzo.enzo.server.api.controller;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.ServiceWithException;
import pl.benzo.enzo.server.api.service.SuccessService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/administrator", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("http://localhost:3000")
public class AdministratorController {
    private final ServiceWithException service;
    private final SuccessService successService;
    @GetMapping(value = "/que-users")
    @ResponseBody
    public ResponseEntity<?> readUsers(){
        Try<List<UserDto>> response = service.findAllUsers();

        return response.isSuccess()
                ? ResponseEntity.ok(response.get())
                : ResponseEntity.internalServerError().body(response.getCause().getMessage());
    }

    @GetMapping(value = "/query-tasks/{creator_id}")
    @ResponseBody
    public ResponseEntity<?> queryTasks(@PathVariable Long creator_id){
        return successService.getAllPersonalTasks(creator_id);
    }
}

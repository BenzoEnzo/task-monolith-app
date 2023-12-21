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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/administrator", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("http://localhost:3000")
public class AdministratorController {
    private final ServiceWithException service;
    @GetMapping(value = "/que-users")
    @ResponseBody
    public ResponseEntity<?> readUsers(){
        Try<List<UserDto>> response = service.findAllUsers();

        return response.isSuccess()
                ? ResponseEntity.ok(response.get())
                : ResponseEntity.internalServerError().body(response.getCause().getMessage());
    }

    @PostMapping(value = "/query-tasks")
    @ResponseBody
    public ResponseEntity<?> queryTasks(@RequestBody TaskDto taskDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.findTasks(taskDto));
    }
}

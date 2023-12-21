package pl.benzo.enzo.server.api.controller;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.SuccessService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/administrator", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("http://localhost:3000")
public class AdministratorController {
    private final SuccessService successService;
    @GetMapping(value = "/que-users")
    public ResponseEntity<?> readUsers(){
        return successService.findAllUsers();
    }

    @GetMapping(value = "/query-tasks/{creator_id}")
    public ResponseEntity<?> queryTasks(@PathVariable Long creator_id){
        return successService.getAllPersonalTasks(creator_id);
    }

    @GetMapping(value = "/query-all-tasks")
    public ResponseEntity<?> queryAllTasks(){
        return successService.findAllTasks();
    }
}

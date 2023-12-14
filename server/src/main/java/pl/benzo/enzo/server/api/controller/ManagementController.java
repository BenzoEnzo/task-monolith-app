package pl.benzo.enzo.server.api.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.service.ManageService;
import pl.benzo.enzo.server.api.service.ServiceWithException;


@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/logged-in")
@Slf4j
public class ManagementController {
    private final ManageService manageService;
    private final ServiceWithException service;

    @PostMapping(value = "/my-account", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> accountMe(@RequestBody AccountDto accountDto){
        return ResponseEntity.status(HttpStatus.OK).body(manageService.getInformationAboutMe(accountDto));
    }

    @PostMapping(value = "/query-tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryTasks(@RequestBody TaskDto taskDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.findTasks(taskDto));
    }



}

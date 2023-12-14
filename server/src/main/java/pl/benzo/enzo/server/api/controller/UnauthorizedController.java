package pl.benzo.enzo.server.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.server.api.model.builder.PersonalInformationBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.ManageService;
import pl.benzo.enzo.server.api.service.ServiceWithException;
import pl.benzo.enzo.server.api.service.basic.LinkServiceBasic;
import pl.benzo.enzo.server.api.service.logic.UploaderService;
import pl.benzo.enzo.server.security.JWT;

import java.util.Objects;

@RestController
@RequestMapping("/api/unauthorized")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UnauthorizedController {
    private final ServiceWithException service;
    private final JWT jwt;
    private final ManageService manageService;
    private final UploaderService uploaderService;
    private final LinkServiceBasic linkServiceBasic;

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
    @PatchMapping(value = "/edit-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserData(@RequestBody UserDto userDto){
        service.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/create-task", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto){
        service.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/query-tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryTasks(@RequestBody TaskDto taskDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.findTasks(taskDto));
    }

    @GetMapping(value = "/query-all-tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllTasks());
    }

    @PostMapping(value = "/my-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accountMe(@RequestBody AccountDto accountDto){
        return ResponseEntity.status(HttpStatus.OK).body(manageService.getInformationAboutMe(accountDto));
    }

    @PostMapping(value = "/create-notification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody NotificationDto notificationDto){
        service.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/query-notification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryNotification(@RequestBody NotificationDto notificationDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.queryNotificationForTask(notificationDto));
    }

    @PostMapping(value = "/join-to-task", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> joinTask(@RequestBody TaskDto taskDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.joinToTask(taskDto));
    }

    @PostMapping(value = "/profile-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("photoId") String photoId)  {
        uploaderService.uploadImageOnServer(file, photoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping(value = "/profile-image/load/{fileName}")
    public ResponseEntity<Resource> getProfilePicture(@PathVariable String fileName) {
        return ResponseEntity.status(HttpStatus.OK).body(uploaderService.loadFile(fileName));
    }

    @GetMapping(value = "/account/confirm/{generatedValue}")
    public ResponseEntity<?> confirmAccount(@PathVariable String generatedValue) {
        linkServiceBasic.confirmAccount(generatedValue);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/read-user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.readUser(userId));
    }
}



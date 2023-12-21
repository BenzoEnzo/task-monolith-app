package pl.benzo.enzo.server.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.server.api.service.SuccessService;
import pl.benzo.enzo.server.api.model.builder.PersonalInformationBuilder;
import pl.benzo.enzo.server.api.model.builder.SuccessResponseBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.service.ServiceWithException;
import pl.benzo.enzo.server.api.service.basic.LinkServiceBasic;
import pl.benzo.enzo.server.api.service.logic.UploaderService;

@RestController
@RequestMapping(path = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final ServiceWithException service;
    private final UploaderService uploaderService;
    private final LinkServiceBasic linkServiceBasic;
    private final SuccessService successService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<SuccessResponseBuilder> signUpUser(@RequestBody AccountDto accountDto){
        return successService.createAccount(accountDto);
    }
    @PostMapping(value = "/sign-in")
        public ResponseEntity<?> signInUser(@RequestBody AccountDto accountDto) {
            return successService.loggIn(accountDto);
        }

    @PostMapping(value = "/my-account")
    public ResponseEntity<PersonalInformationBuilder> accountMe(@RequestBody AccountDto accountDto){
        return successService.userAuthorized(accountDto);
    }

    @PatchMapping(value = "/edit-data")
    public ResponseEntity<SuccessResponseBuilder> createUserData(@RequestBody UserDto userDto){
        return successService.editAccountData(userDto);
    }

    @PostMapping(value = "/create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto){
        service.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/query-tasks/{creator_id}")
    public ResponseEntity<?> queryTasks(@PathVariable Long creator_id){
        return successService.getAllPersonalTasks(creator_id);
    }

    @GetMapping(value = "/query-all-tasks")
    public ResponseEntity<?> queryAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllTasks());
    }

    @PostMapping(value = "/create-notification")
    public ResponseEntity<?> createTask(@RequestBody NotificationDto notificationDto){
        service.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/query-notification")
    public ResponseEntity<?> queryNotification(@RequestBody NotificationDto notificationDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.queryNotificationForTask(notificationDto));
    }

    @PostMapping(value = "/join-to-task")
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

    @GetMapping(value = "/logout")
    public String killSession(){
        return "redirect:/";
    }
}



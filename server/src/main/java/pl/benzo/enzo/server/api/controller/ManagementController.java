package pl.benzo.enzo.server.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.service.ManageService;

@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ManagementController {
    private final ManageService manageService;
    @PostMapping(value = "/my-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accountMe(@RequestBody AccountDto accountDto){
        return ResponseEntity.status(HttpStatus.OK).body(manageService.getInformationAboutMe(accountDto));
    }

    @GetMapping(value = "/logout")
    public String killSession(){
        return "redirect:/";
    }

}

package pl.benzo.enzo.server.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.server.api.service.logic.UploaderService;


import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uploader")
@CrossOrigin("http://localhost:3000")
public class UploaderController {


}

package pl.benzo.enzo.server.api.service.logic.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.server.api.service.logic.UploaderService;
import pl.benzo.enzo.server.util.file.ImageUploader;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UploaderServiceImpl implements UploaderService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void uploadImageOnServer(MultipartFile file, String photoId) {
        final String fileNm = photoId;
        try {
            ImageUploader.storeFile(file, fileNm, uploadDir);
        }  catch(IOException ignored){}

    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            return ImageUploader.loadFile(fileName, uploadDir);
        }catch(IOException ignored){
            return null;
        }
    }
}

package pl.benzo.enzo.server.api.service.logic;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploaderService {
    void uploadImageOnServer(MultipartFile file, String photoId);
    Resource loadFile(String fileName);
}

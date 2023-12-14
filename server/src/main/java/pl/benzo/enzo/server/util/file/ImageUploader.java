package pl.benzo.enzo.server.util.file;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Slf4j
public class ImageUploader {
    private static final Logger logger = LoggerFactory.getLogger(ImageUploader.class);

    public static void storeFile(MultipartFile file, String filename, String uploadDirectory) throws IOException {
        Path directoryPath = Paths.get(uploadDirectory);
        ensureDirectoryExists(directoryPath);

        Path filePath = directoryPath.resolve(filename);
        logger.info("Attempting to write to: " + filePath);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static Resource loadFile(String filename, String uploadDirectory) throws FileNotFoundException {
        final Path path = Paths.get(uploadDirectory);
        Path filePath = path.resolve(filename);
        logger.info("Attempting to read from: " + filePath);

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                filePath = path.resolve("anony.jpg");
                resource = new UrlResource(filePath.toUri());
                return resource;
            }

        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }

    private static void ensureDirectoryExists(Path directoryPath) throws IOException {
        if (Files.exists(directoryPath)) {
            if (!Files.isDirectory(directoryPath)) {
                throw new FileAlreadyExistsException(directoryPath.toString(), null, "Path exists but is not a directory");
            }
        } else {
            Files.createDirectories(directoryPath);
        }
    }
}

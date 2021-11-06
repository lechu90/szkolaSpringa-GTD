package pl.sztukakodu.tasktree.tasks.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class FileSystemStorageService implements StorageService {

    private final Path path;

    public FileSystemStorageService(Path path) {
        this.path = path;
    }

    @Override
    public void saveFile(Long id, MultipartFile file) throws IOException {
        Path targetPath = path.resolve(file.getOriginalFilename());
        log.info("Saving file in {}", targetPath.toAbsolutePath());
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
}

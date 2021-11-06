package pl.sztukakodu.tasktree.tasks.control;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    void saveFile(Long id, MultipartFile file) throws IOException;
}

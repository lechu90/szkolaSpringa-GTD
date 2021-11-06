package pl.sztukakodu.tasktree;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sztukakodu.tasktree.tasks.TasksConfig;
import pl.sztukakodu.tasktree.tasks.control.FileSystemStorageService;

import java.nio.file.Path;

@Slf4j
@Configuration
public class AppConfiguration {

    private TasksConfig tasksConfig;

    public AppConfiguration(TasksConfig tasksConfig) {
        this.tasksConfig = tasksConfig;
    }

    @Bean
    public Clock clock() {
        log.info("Registering Clock as Spring bean");
        return new SystemClock();
    }

    @Bean
    public FileSystemStorageService storage() {
        Path uploadsPath = Path.of(tasksConfig.getUploadsPath());
        log.info("Registering StorageService as Spring bean with path for uploading files: {}", uploadsPath.toAbsolutePath());
        return new FileSystemStorageService(uploadsPath);
    }
}

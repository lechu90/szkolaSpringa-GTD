package pl.sztukakodu.tasktree.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;
}

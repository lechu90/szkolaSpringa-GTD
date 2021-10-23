package pl.sztukakodu.tasktree.tasks.boundary;

import lombok.Data;

@Data
public class UpdateTaskRequest {

    private Long id;

    private String title;

    private String description;
}

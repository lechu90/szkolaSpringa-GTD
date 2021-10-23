package pl.sztukakodu.tasktree.tasks.boundary;

import lombok.Data;

@Data
public class CreateTaskRequest {

    private String title;

    private String description;

    private String author;
}

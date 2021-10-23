package pl.sztukakodu.tasktree.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Task {

    private Long id;

    private String title;

    private String description;

    private String author;

    private LocalDate createdAt;
}

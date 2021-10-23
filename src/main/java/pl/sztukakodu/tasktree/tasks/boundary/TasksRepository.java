package pl.sztukakodu.tasktree.tasks.boundary;

import pl.sztukakodu.tasktree.tasks.entity.Task;

import java.util.List;

public interface TasksRepository {

    List<Task> fetchAll();

    void addTask(Task task);

    Task fetchById(Long id);

    void deleteTaskById(Long id);
}

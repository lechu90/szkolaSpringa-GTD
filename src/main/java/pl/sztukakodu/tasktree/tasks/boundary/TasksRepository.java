package pl.sztukakodu.tasktree.tasks.boundary;

import pl.sztukakodu.tasktree.tasks.entity.Task;

import java.util.List;

public interface TasksRepository {

    List<Task> fetchAll();

    List<Task> filterAllByQuery(String query);

    void addTask(Task task);

    Task fetchById(Long id);

    void deleteTaskById(Long id);

    void updateTask(Long id, String title, String description);
}

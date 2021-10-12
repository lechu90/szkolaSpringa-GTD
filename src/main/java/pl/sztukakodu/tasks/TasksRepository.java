package pl.sztukakodu.tasks;

import java.util.List;

public interface TasksRepository {
    List<Task> fetchAll();

    void addTask(Task task);
}

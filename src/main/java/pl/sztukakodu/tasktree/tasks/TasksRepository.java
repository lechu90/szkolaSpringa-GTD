package pl.sztukakodu.tasktree.tasks;

import java.util.List;

public interface TasksRepository {

    List<Task> fetchAll();

    void addTask(Task task);
}

package pl.sztukakodu.tasktree.tasks.boundary;

import org.springframework.stereotype.Component;
import pl.sztukakodu.tasktree.tasks.entity.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MemoryTasksRepository implements TasksRepository {

    private final Set<Task> tasks = new HashSet<>();

    @Override
    public List<Task> fetchAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public Task fetchById(Long id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No task with given id"));
    }

    @Override
    public void deleteTaskById(Long id) {
        tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .ifPresent(tasks::remove);

    }
}

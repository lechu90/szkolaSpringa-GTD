package pl.sztukakodu.tasktree.tasks.boundary;

import org.springframework.stereotype.Component;
import pl.sztukakodu.tasktree.exceptions.NotFoundException;
import pl.sztukakodu.tasktree.tasks.entity.Task;

import java.util.*;

@Component
public class MemoryTasksRepository implements TasksRepository {

    private final Set<Task> tasks = new HashSet<>();

    @Override
    public List<Task> fetchAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> filterAllByQuery(String query) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(query) || task.getDescription().contains(query))
                .toList();
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public Task fetchById(Long id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No task with given id"));
    }

    @Override
    public void deleteTaskById(Long id) {
        findById(id)
                .ifPresent(tasks::remove);

    }

    @Override
    public void updateTask(Long id, String title, String description) {
        Task task = findById(id).orElseThrow(() -> new NotFoundException(String.format("Task with id[%s] not found", id)));
        task.setTitle(title);
        task.setDescription(description);
    }

    private Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst();
    }
}

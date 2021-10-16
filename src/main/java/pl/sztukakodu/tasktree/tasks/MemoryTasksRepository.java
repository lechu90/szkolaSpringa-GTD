package pl.sztukakodu.tasktree.tasks;

import org.springframework.stereotype.Component;

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
}

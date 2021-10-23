package pl.sztukakodu.tasktree.tasks.control;

import org.springframework.stereotype.Service;
import pl.sztukakodu.tasktree.tasks.boundary.TasksRepository;
import pl.sztukakodu.tasktree.tasks.entity.Task;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final AtomicLong nextTaskId = new AtomicLong(0L);

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void addTask(String title, String description, String author) {
        tasksRepository.addTask(new Task(nextTaskId.getAndIncrement(), title, description, author, LocalDate.now()));
    }
}

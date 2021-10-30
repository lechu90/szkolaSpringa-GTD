package pl.sztukakodu.tasktree.tasks.control;

import org.springframework.stereotype.Service;
import pl.sztukakodu.tasktree.Clock;
import pl.sztukakodu.tasktree.SystemClock;
import pl.sztukakodu.tasktree.tasks.boundary.TasksRepository;
import pl.sztukakodu.tasktree.tasks.entity.Task;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    private final AtomicLong nextTaskId = new AtomicLong(0L);

    private final Clock clock;

    public TasksService(TasksRepository tasksRepository, Clock clock) {
        this.tasksRepository = tasksRepository;
        this.clock = clock;
    }

    public void addTask(String title, String description, String author) {
        tasksRepository.addTask(new Task(nextTaskId.getAndIncrement(), title, description, author, clock.time()));
    }

    public void updateTask(Long id, String title, String description) {
        tasksRepository.updateTask(id, title, description);
    }
}

package pl.sztukakodu.tasktree.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@Slf4j
public class TasksController {

    private final TasksRepository tasksRepository;

    private final TasksConfig config;

    @Value("${app.tasks.propByValue}")
    private String testPropertyByValue;

    public TasksController(TasksRepository tasksRepository, TasksConfig config) {
        this.tasksRepository = tasksRepository;
        this.config = config;
    }

    @GetMapping
    public List<Task> fetchAll() {
        log.info(config.getEndpointGetLogMessage());
        log.info(testPropertyByValue);
        return tasksRepository.fetchAll();
    }

    @GetMapping(path = "/hotReload")
    public String test() {
        return "live reload on build project WORKS!";
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        log.info(config.getEndpointPostLogMessage(), task);
        tasksRepository.addTask(task);
    }

    @PutMapping
    public void updateTask() {
        log.info("task updated");
    }

    @DeleteMapping
    public void deleteTask() {
        log.info("task deleted");
    }
}

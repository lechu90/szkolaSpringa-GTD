package pl.sztukakodu.tasktree.tasks.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.sztukakodu.tasktree.tasks.TasksConfig;
import pl.sztukakodu.tasktree.tasks.control.TasksService;
import pl.sztukakodu.tasktree.tasks.entity.Task;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@Slf4j
public class TasksController {

    private final TasksRepository tasksRepository;

    private final TasksService tasksService;

    private final TasksConfig config;

    @Value("${app.tasks.propByValue}")
    private String testPropertyByValue;

    public TasksController(TasksRepository tasksRepository, TasksService tasksService, TasksConfig config) {
        this.tasksRepository = tasksRepository;
        this.tasksService = tasksService;
        this.config = config;
    }

    @PostConstruct
    public void init() {
        tasksService.addTask("Task 1", "opis 1", "JA");
        tasksService.addTask("Task 2", "opis 2", "JA");
        tasksService.addTask("Task 3", "opis 3", "ktoś");
    }

    @GetMapping
    public List<TaskResponse> fetchAll() {
        log.info(config.getEndpointGetLogMessage());
        log.info(testPropertyByValue);
        return tasksRepository.fetchAll().stream().map(this::convertToTaskResponse).toList();
    }

    @GetMapping(path = "/{id}")
    public TaskResponse fetchById(@PathVariable Long id) {
        log.info("Fetching task with id: {}", id);
        return convertToTaskResponse(tasksRepository.fetchById(id));
    }

    @GetMapping(path = "/hotReload")
    public String test() {
        return "live reload on build project WORKS!";
    }

    @PostMapping
    public void addTask(@RequestBody CreateTaskRequest taskRequest) {
        log.info(config.getEndpointPostLogMessage(), taskRequest);
        tasksService.addTask(taskRequest.getTitle(), taskRequest.getDescription(), taskRequest.getAuthor());
    }

    @PutMapping(path = "/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("task updating...");
        tasksService.updateTask(id, updateTaskRequest.getTitle(), updateTaskRequest.getDescription());

    }

    @DeleteMapping(path = "{id}")
    public void deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id: {}", id);
        tasksRepository.deleteTaskById(id);
    }

    private TaskResponse convertToTaskResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getCreatedAt());
    }
}

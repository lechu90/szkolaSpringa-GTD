package pl.sztukakodu.tasktree.tasks.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sztukakodu.tasktree.exceptions.NotFoundException;
import pl.sztukakodu.tasktree.tasks.TasksConfig;
import pl.sztukakodu.tasktree.tasks.control.TasksService;
import pl.sztukakodu.tasktree.tasks.entity.Task;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

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
    public List<TaskResponse> getTasks(@RequestParam Optional<String> query) {
        log.info(config.getEndpointGetLogMessage());
        log.info(testPropertyByValue);
        log.info("Fetching tasks filtered by {}", query);
        return query.map(tasksService::filterAllByQuery)
                .orElseGet(tasksService::fetchAll)
                .stream().map(this::convertToTaskResponse).toList();
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
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("task updating...");
        try {
            tasksService.updateTask(id, updateTaskRequest.getTitle(), updateTaskRequest.getDescription());
            return ResponseEntity.noContent().build();
        } catch (NotFoundException exception) {
            log.error("Task update failed. Task not found for id = " + id, exception);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
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

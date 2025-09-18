package kg.mega.test_task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.test_task.models.TaskCreateRequest;
import kg.mega.test_task.models.TaskResponse;
import kg.mega.test_task.models.TaskUpdateRequest;
import kg.mega.test_task.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Интерфейс для взаимодействия с задачами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    @Operation(description = "Получение списка всех задач")
    @GetMapping
    public List<TaskResponse> getTasks() {
        return service.getTasks();
    }

    @Operation(description = "Получение задачи по ID")
    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id) {
        return service.getTask(id);
    }

    @Operation(description = "Создание задачи")
    @PostMapping
    public TaskResponse updateTasks(@RequestBody TaskCreateRequest request) {
        return service.create(request);
    }

    @Operation(description = "Обновление задачи")
    @PutMapping
    public TaskResponse updateTasks(@RequestBody TaskUpdateRequest request) {
        return service.update(request);
    }

    @Operation(description = "Удаление задачи")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.delete(id);
    }
}

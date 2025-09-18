package kg.mega.test_task.services;

import kg.mega.test_task.entities.TaskEntity;
import kg.mega.test_task.exceptions.NotFoundException;
import kg.mega.test_task.mappers.TaskMapper;
import kg.mega.test_task.models.TaskCreateRequest;
import kg.mega.test_task.models.TaskResponse;
import kg.mega.test_task.models.TaskUpdateRequest;
import kg.mega.test_task.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "tasks")
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper mapper;

    private final EmailService emailService;


    @Cacheable(key = "'allTasks'")
    public List<TaskResponse> getTasks() {
        return taskRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable(key = "#id")
    public TaskResponse getTask(Long id) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return mapper.toResponse(entity);
    }

    @CacheEvict(allEntries = true)
    public TaskResponse create(TaskCreateRequest request) {
        TaskEntity entity = mapper.toEntity(request);
        TaskEntity result = taskRepository.save(entity);
        emailService.sendTaskEmail(result.getDescription());
        return mapper.toResponse(result);
    }

    @CacheEvict(allEntries = true)
    public TaskResponse update(TaskUpdateRequest request) {
        TaskEntity entity = taskRepository.findById(request.id())
                .orElseThrow(() -> new NotFoundException("Task not found, id:: " + request.id()));

        mapper.update(entity, request);
        return mapper.toResponse(taskRepository.save(entity));
    }

    @CacheEvict(allEntries = true)
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}

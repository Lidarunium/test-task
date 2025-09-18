package kg.mega.test_task.models;

public record TaskResponse(Long id,
                           String description,
                           Boolean isCompleted) {
}

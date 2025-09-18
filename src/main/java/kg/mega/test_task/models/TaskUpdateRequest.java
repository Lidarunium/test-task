package kg.mega.test_task.models;

public record TaskUpdateRequest(Long id,
                                String description,
                                Boolean isCompleted) {
}

package kg.mega.test_task.mappers;

import kg.mega.test_task.entities.TaskEntity;
import kg.mega.test_task.models.TaskCreateRequest;
import kg.mega.test_task.models.TaskResponse;
import kg.mega.test_task.models.TaskUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskResponse toResponse(TaskEntity entity);

    void update(@MappingTarget TaskEntity taskEntity, TaskUpdateRequest request);

    TaskEntity toEntity(TaskCreateRequest request);
}

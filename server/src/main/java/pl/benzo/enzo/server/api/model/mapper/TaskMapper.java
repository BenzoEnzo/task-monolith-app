package pl.benzo.enzo.server.api.model.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    public TaskDto convertToTaskDto(TaskEntity task) {
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setPay(task.getPay());
        taskDto.setStatus(task.getStatus());
        taskDto.setDescription(task.getDescription());

        if (task.getAssignee() != null) {
            taskDto.setAssignee_id(task.getAssignee().getId());
        }
        if (task.getCreator() != null) {
            taskDto.setCreator_id(task.getCreator().getId());
        }

        return taskDto;
    }
}

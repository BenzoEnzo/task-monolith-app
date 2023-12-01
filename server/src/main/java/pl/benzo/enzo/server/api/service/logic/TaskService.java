package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.dto.TaskDto;

import java.util.Set;

public interface TaskService {
    void create(TaskDto taskDto);
    Set<TaskDto> queryTasks(TaskDto taskDto);
}

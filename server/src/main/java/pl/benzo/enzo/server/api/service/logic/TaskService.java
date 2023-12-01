package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.dto.TaskDto;

import java.util.List;
import java.util.Set;

public interface TaskService {
    void create(TaskDto taskDto);
    List<TaskDto> queryTasks(TaskDto taskDto);
    Set<TaskDto> queryAllTasks();
}

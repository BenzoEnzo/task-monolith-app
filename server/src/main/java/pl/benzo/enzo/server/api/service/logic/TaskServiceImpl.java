package pl.benzo.enzo.server.api.service.logic;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.model.mapper.TaskMapper;
import pl.benzo.enzo.server.api.repository.TaskRepository;
import pl.benzo.enzo.server.api.service.basic.UserServiceBasic;
import pl.benzo.enzo.server.util.Status;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserServiceBasic userServiceBasic;
    private final TaskMapper taskMapper;

    @Override
    public void create(TaskDto taskDto) {
        final TaskEntity taskEntity = new TaskEntity();

        taskEntity.setName(taskDto.getName());
        taskEntity.setPay(taskDto.getPay());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setStatus(Status.AVAILABLE);
        taskEntity.setCreator(userServiceBasic.findUserById(taskDto.getCreator_id()));

        taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskDto> queryTasks(TaskDto taskDto) {
        final List<TaskEntity> taskEntity = taskRepository.findAllByCreator_Id(taskDto.getId());
        return taskEntity.stream().map(taskMapper::convertToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public Set<TaskDto> queryAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::convertToTaskDto)
                .collect(Collectors.toSet());
    }
}

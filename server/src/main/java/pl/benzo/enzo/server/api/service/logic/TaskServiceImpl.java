package pl.benzo.enzo.server.api.service.logic;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.repository.TaskRepository;
import pl.benzo.enzo.server.api.service.basic.UserServiceBasic;
import pl.benzo.enzo.server.util.Status;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserServiceBasic userServiceBasic;

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
    public Set<TaskDto> queryTasks(TaskDto taskDto) {
        final Set<TaskEntity> taskEntity = taskRepository.findAllByCreator_Id(taskDto.getCreator_id());
        return taskEntity.stream().map(task -> {
                    TaskDto taskDto1 = new TaskDto();
                    taskDto1.setId(task.getId());
                    taskDto1.setName(task.getName());
                    taskDto1.setPay(task.getPay());
                    taskDto1.setStatus(task.getStatus());
                    taskDto1.setDescription(task.getDescription());

                    if (task.getAssignee() != null) {
                        taskDto1.setAssignee_id(task.getAssignee().getId());
                    }
                    if (task.getCreator() != null) {
                        taskDto1.setCreator_id(task.getCreator().getId());
                    }

                    return taskDto1;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TaskDto> queryAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> {
                    TaskDto taskDto = new TaskDto();
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
                })
                .collect(Collectors.toSet());
    }
}

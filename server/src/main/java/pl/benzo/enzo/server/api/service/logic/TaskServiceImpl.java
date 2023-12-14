package pl.benzo.enzo.server.api.service.logic;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.entity.NotificationEntity;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.model.mapper.TaskMapper;
import pl.benzo.enzo.server.api.repository.NotificationRepository;
import pl.benzo.enzo.server.api.repository.TaskRepository;
import pl.benzo.enzo.server.api.service.basic.UserServiceBasic;
import pl.benzo.enzo.server.util.enumeration.Status;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserServiceBasic userServiceBasic;
    private final TaskMapper taskMapper;
    private final NotificationRepository notificationRepository;
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
    public List<TaskDto> queryTasks(Long creator_id) {
        final List<TaskEntity> taskEntity = taskRepository.findAllByCreator_Id(creator_id);
        return taskEntity.stream().map(taskMapper::convertToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public Set<TaskDto> queryAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::convertToTaskDto)
                .collect(Collectors.toSet());
    }

    public TaskDto joinToTask(TaskDto taskDto){
        final TaskEntity taskEntity = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Bad request"));

        taskEntity.setAssignee(userServiceBasic.findUserById(taskDto.getAssignee_id()));
        taskEntity.setStatus(Status.WAITING_FOR_ACCEPT);
       NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setTitle("Ktoś odpowiedział na twoje zgłoszenie");
        notificationEntity.setContent(taskDto.getDescription());
        List<NotificationEntity> notificationEntityList = taskEntity.getNotifications();
        notificationEntity = notificationRepository.save(notificationEntity);
        notificationEntityList.add(notificationEntity);
        taskEntity.setNotifications(notificationEntityList);
        taskRepository.save(taskEntity);
        return taskDto;
    }
}

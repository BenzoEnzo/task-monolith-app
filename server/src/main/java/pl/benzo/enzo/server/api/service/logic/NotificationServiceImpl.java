package pl.benzo.enzo.server.api.service.logic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;
import pl.benzo.enzo.server.api.model.entity.NotificationEntity;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.repository.NotificationRepository;
import pl.benzo.enzo.server.api.service.basic.TaskServiceBasic;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final TaskServiceBasic taskServiceBasic;

    public List<NotificationDto> queryNotifications(NotificationDto notificationDto){
        final List<NotificationEntity> queryAll = notificationRepository.findAllByTask_Id(notificationDto.getTask_id());
        return queryAll.stream().map(q -> {
            final NotificationDto notfDto = new NotificationDto();
            notfDto.setTitle(q.getTitle());
            notfDto.setDescription(q.getContent());
            return notfDto;
        }).collect(Collectors.toList());
    }

    public void pingNotificationForTask(NotificationDto notificationDto){
        final NotificationEntity notificationEntity = new NotificationEntity();
        final TaskEntity taskEntity = taskServiceBasic.findTaskById(notificationDto.getTask_id());
        notificationEntity.setTitle(notificationDto.getTitle());
        notificationEntity.setContent(notificationDto.getDescription());
        notificationEntity.setTask(taskEntity);
        notificationRepository.save(notificationEntity);
    }
}

package pl.benzo.enzo.server.api.service.logic.implementation;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;
import pl.benzo.enzo.server.api.model.entity.NotificationEntity;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.repository.NotificationRepository;
import pl.benzo.enzo.server.api.service.basic.TaskServiceBasic;
import pl.benzo.enzo.server.api.service.basic.UserServiceBasic;
import pl.benzo.enzo.server.api.service.logic.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final TaskServiceBasic taskServiceBasic;
    private final UserServiceBasic userServiceBasic;

    public List<NotificationDto> queryNotifications(NotificationDto notificationDto){
        final List<NotificationEntity> queryAll = notificationRepository.findAllByTask_Id(notificationDto.getTask_id());
        return queryAll.stream().map(q -> {
            final NotificationDto notfDto = new NotificationDto();
            final UserEntity author = q.getAuthor();
            notfDto.setAuthor_name(author.getName());
            notfDto.setAuthor_id(author.getId());
            notfDto.setTitle(q.getTitle());
            notfDto.setDescription(q.getContent());
            return notfDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void pingNotificationForTask(NotificationDto notificationDto){
        final NotificationEntity notificationEntity = new NotificationEntity();
        final TaskEntity taskEntity = taskServiceBasic.findTaskById(notificationDto.getTask_id());
        final UserEntity userEntity = userServiceBasic.findUserById(notificationDto.getAuthor_id());

        notificationEntity.setTitle(notificationDto.getTitle());
        notificationEntity.setContent(notificationDto.getDescription());
        notificationEntity.setTask(taskEntity);
        notificationEntity.setAuthor(userEntity);
        notificationRepository.save(notificationEntity);
    }
}

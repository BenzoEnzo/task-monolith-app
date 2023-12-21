package pl.benzo.enzo.server.api.model.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;
import pl.benzo.enzo.server.api.model.entity.NotificationEntity;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    public NotificationDto mapToNotificationDto(NotificationEntity notificationEntity){
        final NotificationDto notificationDto = new NotificationDto();

        notificationDto.setAuthor_name(notificationEntity.getAuthor().getName());
        notificationDto.setAuthor_id(notificationEntity.getAuthor().getId());
        notificationDto.setUser_id(notificationEntity.getInvitedPerson().getId());
        notificationDto.setTitle(notificationEntity.getTitle());
        notificationDto.setDescription(notificationEntity.getContent());
        notificationDto.setAddressed_to(notificationEntity.getInvitedPerson().getId());

        return notificationDto;
    }
}

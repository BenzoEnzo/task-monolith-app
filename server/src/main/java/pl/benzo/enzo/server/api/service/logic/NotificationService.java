package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.builder.SuccessResponseBuilder;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    void pingNotificationForTask(NotificationDto notificationDto);

    List<NotificationDto> queryNotifications(NotificationDto notificationDto);

    SuccessResponseBuilder pingNotificationForUser(NotificationDto notificationDto);

    List<NotificationDto> queryInvitations(Long user_id);
}
package pl.benzo.enzo.server.api.model.dto;


import lombok.Builder;
import lombok.Data;
import pl.benzo.enzo.server.api.model.entity.NotificationEntity;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;


@Builder
@Data
public class EntitiesBuilder {
    private NotificationEntity notification;
    private TaskEntity task;
    private UserEntity user;
}

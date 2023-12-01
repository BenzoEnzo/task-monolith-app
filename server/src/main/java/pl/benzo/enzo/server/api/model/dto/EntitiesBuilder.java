package pl.benzo.enzo.server.api.model.dto;


import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class EntitiesBuilder {
    private AccountDto accountDto;
    private NotificationDto notificationDto;
    private TaskDto taskDto;
    private UserDto userDto;
}

package pl.benzo.enzo.server.api.model.builder;


import lombok.Builder;
import lombok.Data;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.NotificationDto;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;


@Builder
@Data
public class EntitiesBuilder {
    private AccountDto accountDto;
    private NotificationDto notificationDto;
    private TaskDto taskDto;
    private UserDto userDto;
}

package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.builder.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.dto.ReadUserDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    void update(UserDto userDto);

    ReadUserDto readUser(Long userId);
}

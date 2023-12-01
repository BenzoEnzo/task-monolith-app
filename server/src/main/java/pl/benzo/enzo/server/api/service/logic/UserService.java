package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.dto.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto create(EntitiesBuilder entitiesBuilder);
}

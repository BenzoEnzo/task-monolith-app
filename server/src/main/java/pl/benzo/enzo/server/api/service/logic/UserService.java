package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.dto.UserBuilder;

import java.util.List;

public interface UserService {
    List<UserBuilder> findAll();
}

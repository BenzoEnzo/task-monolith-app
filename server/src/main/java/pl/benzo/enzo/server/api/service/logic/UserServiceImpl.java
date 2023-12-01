package pl.benzo.enzo.server.api.service.logic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.UserBuilder;
import pl.benzo.enzo.server.api.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserBuilder> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> UserBuilder.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .build())
                .collect(Collectors.toList());
    }
}

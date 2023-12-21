package pl.benzo.enzo.server.api.service.logic.implementation;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.builder.EntitiesBuilder;
import pl.benzo.enzo.server.api.model.dto.AccountDto;
import pl.benzo.enzo.server.api.model.dto.ReadUserDto;
import pl.benzo.enzo.server.api.model.dto.TaskDto;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.model.mapper.TaskMapper;
import pl.benzo.enzo.server.api.repository.UserRepository;
import pl.benzo.enzo.server.api.service.basic.AccountServiceBasic;
import pl.benzo.enzo.server.api.service.basic.UserServiceBasic;
import pl.benzo.enzo.server.api.service.logic.TaskService;
import pl.benzo.enzo.server.api.service.logic.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserServiceBasic userServiceBasic;
    private final TaskService taskService;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserEntity -> new UserDto(UserEntity.getId(),UserEntity.getName(), UserEntity.getScore()))
                .collect(Collectors.toList());
    }

    @Override
    public void create(UserDto userDto) {
        final Optional<UserEntity> userEntity = userRepository.findById(userDto.getUser_id());
        userEntity.ifPresent(entity -> entity.setName(userDto.getName()));
        userRepository.save(userEntity.get());
    }

    @Override
    public ReadUserDto readUser(Long userId) {
        final UserEntity userEntity = userServiceBasic.findUserById(userId);
        final List<TaskDto> taskDtos = taskService.queryTasks(userId);

        return ReadUserDto.builder()
                .name(userEntity.getName())
                .score(userEntity.getScore())
                .photoId(userEntity.getAccount().getPhotoId())
                .createdTasks(taskDtos).build();
    }

}

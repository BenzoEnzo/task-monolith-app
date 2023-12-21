package pl.benzo.enzo.server.api.service.logic.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.builder.SuccessResponseBuilder;
import pl.benzo.enzo.server.api.model.dto.UserDto;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.model.mapper.UserMapper;
import pl.benzo.enzo.server.api.service.basic.UserServiceBasic;
import pl.benzo.enzo.server.api.service.logic.FriendService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final UserServiceBasic userServiceBasic;
    private final UserMapper userMapper;
    @Override
    public Set<UserDto> getAllMyFriends(Long user_id) {
        final UserEntity user = userServiceBasic.findUserById(user_id);
        final Set<UserEntity> friends = user.getFriends();

        return friends
                .stream()
                .map(userMapper::convertToUserDto)
                .collect(Collectors.toSet());
        }


    @Override
    public SuccessResponseBuilder addFriend(Long friend_id) {
        return null;
    }

    @Override
    public SuccessResponseBuilder deleteFriend(Long friend_id) {
        return null;
    }
}

package pl.benzo.enzo.server.api.service.logic;



import pl.benzo.enzo.server.api.model.builder.SuccessResponseBuilder;
import pl.benzo.enzo.server.api.model.dto.UserDto;

import java.util.Set;

public interface FriendService {

    Set<UserDto> getAllMyFriends(Long user_id);
    SuccessResponseBuilder addFriend(Long friend_id);
    SuccessResponseBuilder deleteFriend(Long friend_id);
}

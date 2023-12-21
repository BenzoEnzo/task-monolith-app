package pl.benzo.enzo.server.api.service.basic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.api.repository.UserRepository;
import pl.benzo.enzo.server.exception.user.UserDoesntExistException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceBasic {
    private final UserRepository userRepository;
    public UserEntity findUserById(Long user_id)
    {
        return userRepository.findById(user_id).orElseThrow(() -> new UserDoesntExistException(
                "Użytkownik o " + user_id + " id nie istnieje"
        ));
    }
}

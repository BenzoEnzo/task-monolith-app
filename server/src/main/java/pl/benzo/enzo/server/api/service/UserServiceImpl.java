package pl.benzo.enzo.server.api.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
}

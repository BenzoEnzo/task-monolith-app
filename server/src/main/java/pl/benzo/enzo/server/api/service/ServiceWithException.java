package pl.benzo.enzo.server.api.service;


import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.dto.UserBuilder;
import pl.benzo.enzo.server.api.service.logic.UserService;

import java.util.List;

import static jdk.internal.net.http.common.Log.logError;

@Service
@RequiredArgsConstructor
public class ServiceWithException {
    private final UserService userService;

    public Try<List<UserBuilder>> findAllUsers() {
        return Try.of(userService::findAll)
                .onFailure(ex -> logError(ex));
    }

}

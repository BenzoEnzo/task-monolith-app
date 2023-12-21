package pl.benzo.enzo.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserAuthorizationException extends RuntimeException {
    public UserAuthorizationException(String message){
        super(message);
    }
}


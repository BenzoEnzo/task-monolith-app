package pl.benzo.enzo.server.exception.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class UserDoesntExistException extends RuntimeException{
    public UserDoesntExistException(String message){
        super(message);
    }
}

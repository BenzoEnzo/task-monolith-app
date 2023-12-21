package pl.benzo.enzo.server.exception.account;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MailAlreadyExistException extends RuntimeException {
    public MailAlreadyExistException(String message){
        super(message);
    }
}

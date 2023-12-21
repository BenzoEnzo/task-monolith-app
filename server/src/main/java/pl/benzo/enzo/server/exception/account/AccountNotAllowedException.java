package pl.benzo.enzo.server.exception.account;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AccountNotAllowedException extends RuntimeException {
    public AccountNotAllowedException(String message){
        super(message);
    }
}

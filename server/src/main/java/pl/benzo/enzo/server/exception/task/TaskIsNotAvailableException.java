package pl.benzo.enzo.server.exception.task;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskIsNotAvailableException extends RuntimeException {
    public TaskIsNotAvailableException(String message){
        super(message);
    }
}

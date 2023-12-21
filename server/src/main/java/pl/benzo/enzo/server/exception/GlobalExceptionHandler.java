package pl.benzo.enzo.server.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.benzo.enzo.server.api.model.dto.ErrorResponseDto;
import pl.benzo.enzo.server.exception.account.MailAlreadyExistException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleNotAllowedAccountException(RuntimeException exception, WebRequest webRequest){
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .apiPath(webRequest.getDescription(false))
                .statusCode(HttpStatus.BAD_REQUEST)
                .at(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
}

package pl.benzo.enzo.server.exception;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.benzo.enzo.server.api.model.builder.ErrorResponseBuilder;

import java.time.LocalDateTime;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseBuilder> handleNotAllowedAccountException(RuntimeException exception, WebRequest webRequest){
        final ErrorResponseBuilder errorResponseBuilder = ErrorResponseBuilder.builder()
                .message(exception.getMessage())
                .apiPath(webRequest.getDescription(false))
                .statusCode(HttpStatus.BAD_REQUEST)
                .at(LocalDateTime.now()).build();

        LOGGER.error(exception.getMessage() + " at " + errorResponseBuilder.getAt());

        return new ResponseEntity<>(errorResponseBuilder,HttpStatus.BAD_REQUEST);
    }
}

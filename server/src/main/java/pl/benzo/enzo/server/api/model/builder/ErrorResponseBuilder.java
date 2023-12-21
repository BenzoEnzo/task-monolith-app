package pl.benzo.enzo.server.api.model.builder;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponseBuilder {
    private String apiPath;
    private String message;
    private HttpStatus statusCode;
    private LocalDateTime at;
}

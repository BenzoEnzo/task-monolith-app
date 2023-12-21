package pl.benzo.enzo.server.api.model.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponseDto {
    private String apiPath;
    private String message;
    private HttpStatus statusCode;
    private LocalDateTime at;
}

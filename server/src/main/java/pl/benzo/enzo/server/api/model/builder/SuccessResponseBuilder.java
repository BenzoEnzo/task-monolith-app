package pl.benzo.enzo.server.api.model.builder;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SuccessResponseBuilder {
    private HttpStatus httpStatus;
    private String msg;
    private Object body;
}

package pl.benzo.enzo.server.api.model.dto;

import lombok.Builder;
import lombok.Data;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.util.Status;

import java.math.BigDecimal;


@Builder
@Data
public class TaskBuilder {
    private Long id;
    private String name;
    private String description;
    private BigDecimal pay;
    private Status status;
    private UserEntity user;
}

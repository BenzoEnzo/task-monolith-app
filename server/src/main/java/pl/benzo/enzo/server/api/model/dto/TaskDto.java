package pl.benzo.enzo.server.api.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.benzo.enzo.server.api.model.entity.UserEntity;
import pl.benzo.enzo.server.util.Status;

import java.math.BigDecimal;


@Getter
@Setter
@RequiredArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal pay;
    private Status status;
    private Long creator_id;
    private Long assignee_id;
}

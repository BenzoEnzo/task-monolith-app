package pl.benzo.enzo.server.api.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.With;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;

import java.util.Set;

@Builder
@Data
@With
public class ReadUserDto {
    private String name;
    private Integer score;
    private String photoId;
    private Set<TaskDto> createdTasks;
}

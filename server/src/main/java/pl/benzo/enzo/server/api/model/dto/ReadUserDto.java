package pl.benzo.enzo.server.api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;


@Builder
@Data
@With
public class ReadUserDto {
    private String name;
    private Integer score;
    private String photoId;
    private List<TaskDto> createdTasks;
}

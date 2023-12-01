package pl.benzo.enzo.server.api.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationBuilder {
    private Long id;
    private String title;
    private String description;
}

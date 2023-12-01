package pl.benzo.enzo.server.api.model.dto;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class NotificationDto {
    private Long id;
    private String title;
    private String description;
    private Long task_id;
}

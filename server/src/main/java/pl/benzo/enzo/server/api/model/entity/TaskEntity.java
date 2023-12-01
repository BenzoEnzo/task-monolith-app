package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.benzo.enzo.server.util.Status;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@Table(name = "TASKS")
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal pay;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userRelation;

    @ManyToMany
    @JoinTable(
            name = "task_notifications",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<NotificationEntity> notifications;
}

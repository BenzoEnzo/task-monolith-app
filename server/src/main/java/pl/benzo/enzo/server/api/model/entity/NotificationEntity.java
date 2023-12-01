package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "NOTIFICATIONS")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

}

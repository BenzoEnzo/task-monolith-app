package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.benzo.enzo.server.util.Status;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Table(name = "TASKS")
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    private String name;
    private String description;
    private BigDecimal pay;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private UserEntity assignee;

    @ManyToMany(mappedBy = "tasks")
    private Set<NotificationEntity> notifications;
}

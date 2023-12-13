package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.benzo.enzo.server.util.enumeration.Status;

import java.math.BigDecimal;
import java.util.List;

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


    @OneToMany(cascade = CascadeType.ALL)
    private List<NotificationEntity> notifications;
}

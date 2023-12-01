package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "userRelation")
    private List<TaskEntity> tasks;

    @OneToMany(mappedBy = "userRelation")
    private List<NotificationEntity> notifications;
}

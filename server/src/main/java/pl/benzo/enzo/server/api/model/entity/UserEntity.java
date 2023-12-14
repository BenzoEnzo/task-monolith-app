package pl.benzo.enzo.server.api.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private Integer score;
    private String name;
    private String lastName;
    @Column(unique = true)
    private Long pesel;
    @Column(unique = true)
    private Long phone;


    @OneToOne(mappedBy = "user")
    private AccountEntity account;

    @OneToMany(mappedBy = "creator")
    private Set<TaskEntity> createdTasks = new HashSet<>();

    @OneToMany(mappedBy = "assignee")
    private Set<TaskEntity> assignedTasks = new HashSet<>();


}

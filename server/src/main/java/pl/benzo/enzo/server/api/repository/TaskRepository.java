package pl.benzo.enzo.server.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long>{
}

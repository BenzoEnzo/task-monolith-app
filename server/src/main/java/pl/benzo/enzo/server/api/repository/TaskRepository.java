package pl.benzo.enzo.server.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long>{
    List<TaskEntity> findAllByCreator_Id(Long id);
}

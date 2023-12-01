package pl.benzo.enzo.server.api.service.basic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.entity.TaskEntity;
import pl.benzo.enzo.server.api.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskServiceBasic {
    private final TaskRepository taskRepository;
    public TaskEntity findTaskById(Long task_id){
        return taskRepository.findById(task_id).orElseThrow(() -> new IllegalArgumentException("Null"));
    }
}

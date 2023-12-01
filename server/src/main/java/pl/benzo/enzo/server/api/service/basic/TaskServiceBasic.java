package pl.benzo.enzo.server.api.service.basic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskServiceBasic {
    private final TaskRepository taskRepository;
}

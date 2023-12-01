package pl.benzo.enzo.server.api.service.logic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.repository.TaskRepository;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl {
    private final TaskRepository taskRepository;
}

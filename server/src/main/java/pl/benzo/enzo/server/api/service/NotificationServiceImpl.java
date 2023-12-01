package pl.benzo.enzo.server.api.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.repository.NotificationRepository;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl {
    private final NotificationRepository notificationRepository;
}

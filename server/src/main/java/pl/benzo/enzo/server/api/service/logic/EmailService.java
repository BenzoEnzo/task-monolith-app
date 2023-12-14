package pl.benzo.enzo.server.api.service.logic;

public interface EmailService {
    void sendEmail(String to, String topic, String confirmationLink);
}

package pl.benzo.enzo.server.api.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mail;
    @Override
    public void sendEmail(String to, String topic, String confirmationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(to);
        message.setSubject(topic);
        message.setText(confirmationLink);
        javaMailSender.send(message);
    }
}

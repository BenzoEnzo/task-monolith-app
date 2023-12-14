package pl.benzo.enzo.server.api.service.logic;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mail;
    @Override
    public void sendEmail(String to, String topic, String confirmationLink) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(mail);
            helper.setTo(to);
            helper.setSubject(topic);
            helper.setText(confirmationLink, true);
            javaMailSender.send(message);
        } catch(MessagingException ignored){
        }
    }
}

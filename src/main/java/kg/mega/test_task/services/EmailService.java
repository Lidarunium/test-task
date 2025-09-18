package kg.mega.test_task.services;

import kg.mega.test_task.configs.properties.CustomUserDetailsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final CustomUserDetailsProperties detailsProperties;

    @Async
    public void sendTaskEmail(String taskDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(detailsProperties.getEmail());
        message.setSubject("Новая задача создана!");
        message.setText("Детали задачи: " + taskDetails);
        message.setFrom("a.nuradil.m@gmail.com");

        mailSender.send(message);
    }
}

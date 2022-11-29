package Email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

public class Email {
    private final EmailService emailService;

    public Email(EmailService emailService) {
        this.emailService = emailService;
    }

    public void Run() {
        SpringApplication.run(Email.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() throws MessagingException {
        emailService.sendEmailWithAttachment("The program ended with an error or exception!","kristinav2345@gmail.com",
                "Program logs",
                "D:\\Java\\JavaProgr\\Laboratory-Work_4-8\\coffeevan\\logs\\coffeeLogs\\logs.log");
    }
}

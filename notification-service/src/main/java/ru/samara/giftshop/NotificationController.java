package ru.samara.giftshop;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.samara.giftshop.model.Mail;
import ru.samara.giftshop.service.EmailService;

@Controller
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendEmail(){
        emailService.sendSimpleMessage(
                Mail.builder()
                        .to("shirokih_i@mail.ru")
                        .subject("sub")
                        .text("text")
                        .build());
    }
}

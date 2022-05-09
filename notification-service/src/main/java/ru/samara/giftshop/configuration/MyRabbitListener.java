package ru.samara.giftshop.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.samara.giftshop.dto.MyMail;
import ru.samara.giftshop.service.EmailService;

@EnableRabbit
@Component
@AllArgsConstructor
@Slf4j
public class MyRabbitListener {

    private final EmailService emailService;
    private final ObjectMapper jsonMapper;

    @RabbitListener(queues = "notificationQueue")
    public void processMyQueue(String message) throws JsonProcessingException {
        log.info("Received from notificationQueue: {}", message);
        MyMail m = jsonMapper.readValue(message,MyMail.class);
        emailService.sendSimpleMessage(m);
    }
}

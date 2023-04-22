package ru.samara.giftshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.MyMail;
import ru.samara.giftshop.entity.Order;

@Service
public class MailService extends BaseService {

    @Value("${spring.email-admin}")
    private String emailAdmin;

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    private void sendMailToQueue(MyMail mail) {
        try {
            rabbitTemplate.convertAndSend("notificationQueue", jsonMapper.writeValueAsString(mail));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void sendOrderCreationEmail(Order order) {
        StringBuilder text = new StringBuilder("Адрес доставки:\n" + order.getAddress() + "\n\nСписок товаров:\n");
        order.getProducts().forEach(p->{
            text.append(p.getName()).append(";\n");
        });
        MyMail mail = MyMail.builder()
                .to(emailAdmin)
                .subject("Создан новый заказ")
                .text(text.toString())
                .build();
        sendMailToQueue(mail);
    }

    public void sendOrderStatusUpdateEmail(Order order) {
        StringBuilder text = new StringBuilder("Статус вашего заказа: ").append(order.getStatus().getStatusRuName());
        text.append("\nАдрес доставки:\n").append(order.getAddress()).append("\n\nСписок товаров:\n");
        order.getProducts().forEach(p->{
            text.append(p.getName()).append(";\n");
        });
        MyMail mail = MyMail.builder()
                .to(order.getUser().getUsername())
                .subject("Ваш заказ обновлен")
                .text(text.toString())
                .build();
        sendMailToQueue(mail);
    }
}

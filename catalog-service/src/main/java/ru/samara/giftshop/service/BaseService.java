package ru.samara.giftshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    protected RabbitTemplate rabbitTemplate;
    @Autowired
    protected ObjectMapper jsonMapper;
}

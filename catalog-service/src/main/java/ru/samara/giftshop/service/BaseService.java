package ru.samara.giftshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataValidationResponse;

@Service
public class BaseService {
    @Autowired
    protected RabbitTemplate rabbitTemplate;
    @Autowired
    protected ObjectMapper jsonMapper;

    protected void notNull(Object o) {
        if (o == null) {
            throw new ApiException(DataValidationResponse.INVALID_REQUEST);
        }
    }
}

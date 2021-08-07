package br.com.bruno.demo.rabbit.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

    public void sendMessage(String nameQueue, Object message) {
        try {
            log.info("m=sendMessage, stage=init");
            this.rabbitTemplate.setMessageConverter(this.messageConverter);
            this.rabbitTemplate.convertAndSend(nameQueue, message);
            log.info("m=sendMessage, stage=completed");
        } catch (Exception ex) {
            log.error("m=sendMessage, stage=error, message={}", ex.getMessage(), ex);
            throw ex;
        }
    }
}

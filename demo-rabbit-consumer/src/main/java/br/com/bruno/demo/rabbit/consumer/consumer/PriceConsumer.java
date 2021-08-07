package br.com.bruno.demo.rabbit.consumer.consumer;

import br.com.bruno.demo.rabbit.consumer.dto.PriceDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PriceConsumer {

    @Autowired
    private Gson gson;

    @RabbitListener(queues = "${queue.name.price}")
    private void consumerPrice(Message message) {
        try {
            log.info("m=consumerPrice, stage=init");
            logPrice(message);
            log.info("m=consumerPrice, stage=completed");
        } catch (Exception ex) {
            log.error("m=consumerPrice, stage=error, message={}", ex.getMessage(), ex);
            throw ex;
        }
    }

    private void logPrice(Message message) {
        PriceDTO priceDTO = gson.fromJson(new String(message.getBody()), PriceDTO.class);
        log.info("Código do preço: {}", priceDTO.getIdPrice());
        log.info("Código do produto: {}", priceDTO.getIdProduct());
        log.info("Valor do produto: {}", priceDTO.getValue());
    }
}

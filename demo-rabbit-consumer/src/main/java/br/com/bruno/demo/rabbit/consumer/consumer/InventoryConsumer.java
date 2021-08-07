package br.com.bruno.demo.rabbit.consumer.consumer;

import br.com.bruno.demo.rabbit.consumer.dto.InventoryDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryConsumer {

    @Autowired
    private Gson gson;

    @RabbitListener(queues = "${queue.name.inventory}")
    private void consumerInventory(Message message) {
        try {
            log.info("m=consumerInventory, stage=init");
            logInventory(message);
            log.info("m=consumerInventory, stage=completed");
        } catch (Exception ex) {
            log.error("m=consumerInventory, stage=error, message={}", ex.getMessage(), ex);
            throw ex;
        }
    }

    private void logInventory(Message message) {
        InventoryDTO inventoryDTO = gson.fromJson(new String(message.getBody()), InventoryDTO.class);
        log.info("Código do produto: {}", inventoryDTO.getIdProduct());
        log.info("Nome do produto: {}", inventoryDTO.getNameProduct());
        log.info("Descrição do produto: {}", inventoryDTO.getDescriptionProduct());
        log.info("Quantidade em estoque: {}", inventoryDTO.getQuantityInInventory());
    }
}

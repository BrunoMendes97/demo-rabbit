package br.com.bruno.demo.rabbit.producer.controller;

import br.com.bruno.demo.rabbit.producer.dto.InventoryDTO;
import br.com.bruno.demo.rabbit.producer.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Value("${queue.name.inventory}")
    private String nameQueueInventory;

    @PutMapping
    private ResponseEntity<?> alterInventory(@RequestBody InventoryDTO inventoryDTO) {
        try {
            log.info("m=alterInventory, stage=init");
            this.rabbitMQService.sendMessage(nameQueueInventory, inventoryDTO);
            log.info("m=alterInventory, stage=completed");
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("m=alterInventory, stage=error, message={}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}

package br.com.bruno.demo.rabbit.producer.controller;

import br.com.bruno.demo.rabbit.producer.dto.PriceDTO;
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
@RequestMapping(value = "/v1/price")
@Slf4j
public class PriceController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Value("${queue.name.price}")
    private String nameQueuePrice;

    @PutMapping
    private ResponseEntity<?> alterPrice(@RequestBody PriceDTO priceDTO) {
        try {
            log.info("m=alterPrice, stage=init");
            this.rabbitMQService.sendMessage(nameQueuePrice, priceDTO);
            log.info("m=alterPrice, stage=completed");
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("m=alterPrice, stage=error, message={}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}

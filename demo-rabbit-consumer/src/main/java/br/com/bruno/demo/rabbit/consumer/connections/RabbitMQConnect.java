package br.com.bruno.demo.rabbit.consumer.connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnect {

    @Value("${exchange.name}")
    private String nameExchange;

    @Value("${queue.name.inventory}")
    private String nameQueueInventory;

    @Value("${queue.name.price}")
    private String nameQueuePrice;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue queue(String nameQueue) {
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange exchange() {
        return new DirectExchange(nameExchange);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @Bean
    private void addQueues() {
        Queue queueInventory = this.queue(this.nameQueueInventory);
        Queue queuePrice = this.queue(this.nameQueuePrice);
        DirectExchange exchange = this.exchange();

        this.amqpAdmin.declareQueue(queueInventory);
        this.amqpAdmin.declareQueue(queuePrice);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(this.binding(queueInventory, exchange));
        this.amqpAdmin.declareBinding(this.binding(queuePrice, exchange));
    }
}

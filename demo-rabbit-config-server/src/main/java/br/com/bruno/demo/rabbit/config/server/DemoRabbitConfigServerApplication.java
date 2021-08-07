package br.com.bruno.demo.rabbit.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DemoRabbitConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRabbitConfigServerApplication.class, args);
    }

}

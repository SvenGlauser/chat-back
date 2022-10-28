package ch.sven.chat.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ch.sven.chat.application",
        "ch.sven.chat.infrastructure"
})
@EntityScan(basePackages = "ch.sven.chat.infrastructure")
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class);
    }
}

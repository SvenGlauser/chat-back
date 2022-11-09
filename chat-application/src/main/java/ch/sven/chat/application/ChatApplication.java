package ch.sven.chat.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {
        "ch.sven.chat.application",
        "ch.sven.chat.infrastructure",
        "ch.sven.chat.security",
})
@EnableJpaRepositories(basePackages = "ch.sven.chat.infrastructure.hibernate")
@EntityScan(basePackages = "ch.sven.chat.infrastructure")
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class);
    }
}

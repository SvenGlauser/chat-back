package ch.sven.chat.application.message.configuration;

import ch.sven.chat.domain.message.repository.MessageRepository;
import ch.sven.chat.domain.message.service.MessageService;
import ch.sven.chat.domain.message.service.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageBeanConfiguration {
    @Bean
    public MessageService messageService(MessageRepository messageRepository) {
        return new MessageServiceImpl(messageRepository);
    }
}

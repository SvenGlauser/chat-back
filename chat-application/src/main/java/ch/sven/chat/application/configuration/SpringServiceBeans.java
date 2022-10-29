package ch.sven.chat.application.configuration;

import ch.sven.chat.domain.message.repository.MessageRepository;
import ch.sven.chat.domain.message.service.MessageService;
import ch.sven.chat.domain.message.service.MessageServiceImpl;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import ch.sven.chat.domain.utilisateur.service.UtilisateurService;
import ch.sven.chat.domain.utilisateur.service.UtilisateurServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringServiceBeans {
    @Bean
    public MessageService messageService(MessageRepository messageRepository) {
        return new MessageServiceImpl(messageRepository);
    }

    @Bean
    public UtilisateurService utilisateurService(UtilisateurRepositoy utilisateurRepositoy) {
        return new UtilisateurServiceImpl(utilisateurRepositoy);
    }
}

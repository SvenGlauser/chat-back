package ch.sven.chat.application.utilisateur.configuration;

import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import ch.sven.chat.domain.utilisateur.service.UtilisateurService;
import ch.sven.chat.domain.utilisateur.service.UtilisateurServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurBeanConfiguration {
    @Bean
    public UtilisateurService utilisateurService(UtilisateurRepositoy utilisateurRepositoy) {
        return new UtilisateurServiceImpl(utilisateurRepositoy);
    }
}

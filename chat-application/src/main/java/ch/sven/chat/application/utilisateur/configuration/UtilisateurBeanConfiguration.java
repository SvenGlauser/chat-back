package ch.sven.chat.application.utilisateur.configuration;

import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.domain.utilisateur.service.UtilisateurService;
import ch.sven.chat.domain.utilisateur.service.UtilisateurServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurBeanConfiguration {
    @Bean
    public UtilisateurService utilisateurService(UtilisateurRepository utilisateurRepository) {
        return new UtilisateurServiceImpl(utilisateurRepository);
    }
}

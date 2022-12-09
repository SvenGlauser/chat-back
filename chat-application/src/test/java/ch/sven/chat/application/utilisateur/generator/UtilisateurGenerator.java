package ch.sven.chat.application.utilisateur.generator;

import ch.sven.chat.application.configuration.SecurityConfigurationTest;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.infrastructure.hibernate.utilisateur.UtilisateurRepositoryHibernate;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurGenerator {
    private final UtilisateurRepositoryHibernate utilisateurRepositoy;

    public UtilisateurDto generateTestUser() {
        return generateUser(SecurityConfigurationTest.USER_TEST);
    }

    public UtilisateurDto generate(String nom) {
        return generateUser(nom);
    }

    private UtilisateurDto generateUser(String nom) {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setKeycloakId(UUID.randomUUID().toString());
        utilisateur.setNom(nom);
        utilisateur.setPrenom("Test");
        utilisateur.setEmail("test@test.com");
        utilisateur.setImageUrl("http://google.com");
        utilisateur.setTheme(Theme.SOMBRE);
        return new UtilisateurDto(utilisateurRepositoy.save(utilisateur).toDomain());
    }
}

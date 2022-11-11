package ch.sven.chat.application.common;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.infrastructure.hibernate.utilisateur.UtilisateurRepositoryHibernate;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EntityTest {

    @Autowired
    UtilisateurRepositoryHibernate utilisateurRepositoryHibernate;

    @Test
    void localDateTimeGeneration() {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setKeycloakId(UUID.randomUUID().toString());
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setImageUrl("https://google.com");

        utilisateur = utilisateurRepositoryHibernate.save(utilisateur);

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getCreation()).isNotNull();

        utilisateur.setNom("Nouveau nom");

        utilisateur = utilisateurRepositoryHibernate.save(utilisateur);

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getModification()).isNotNull();
    }
}

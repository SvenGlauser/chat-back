package ch.sven.chat.application.common;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.infrastructure.hibernate.utilisateur.UtilisateurRepositoryHibernate;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EntityTest {

    @Autowired
    UtilisateurRepositoryHibernate utilisateurRepositoryHibernate;

    @Test
    void localDateTimeGeneration() {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setImageUrl("https://google.com");

        LocalDateTime before = LocalDateTime.now();

        utilisateur = utilisateurRepositoryHibernate.save(utilisateur);

        LocalDateTime after = LocalDateTime.now();

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getCreation()).isAfterOrEqualTo(before);
        assertThat(utilisateur.getCreation()).isBeforeOrEqualTo(after);

        utilisateur.setNom("Nouveau nom");

        before = LocalDateTime.now();

        utilisateur = utilisateurRepositoryHibernate.save(utilisateur);

        after = LocalDateTime.now();

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getModification()).isAfterOrEqualTo(before);
        assertThat(utilisateur.getModification()).isBeforeOrEqualTo(after);
    }
}

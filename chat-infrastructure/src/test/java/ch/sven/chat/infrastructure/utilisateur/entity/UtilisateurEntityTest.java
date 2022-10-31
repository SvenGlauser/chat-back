package ch.sven.chat.infrastructure.utilisateur.entity;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UtilisateurEntityTest {

    @Test
    void utilisateurEntity() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");
        utilisateur.setImageUrl("https://google.com");

        UtilisateurEntity result = new UtilisateurEntity(utilisateur);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(utilisateur.getId());
        assertThat(result.getNom()).isEqualTo(utilisateur.getNom());
        assertThat(result.getPrenom()).isEqualTo(utilisateur.getPrenom());
        assertThat(result.getTheme()).isEqualTo(utilisateur.getTheme());
        assertThat(result.getImageUrl()).isEqualTo(utilisateur.getImageUrl());
    }

    @Test
    void toDomainEntity() {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");
        utilisateur.setImageUrl("https://google.com");

        Utilisateur result = utilisateur.toDomain();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(utilisateur.getId());
        assertThat(result.getNom()).isEqualTo(utilisateur.getNom());
        assertThat(result.getPrenom()).isEqualTo(utilisateur.getPrenom());
        assertThat(result.getTheme()).isEqualTo(utilisateur.getTheme());
        assertThat(result.getImageUrl()).isEqualTo(utilisateur.getImageUrl());
    }
}
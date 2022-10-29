package ch.sven.chat.application.utilisateur.dto;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UtilisateurDtoTest {

    @Test
    void utilisateurDto() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");
        utilisateur.setImageUrl("https://google.com");

        UtilisateurDto result = new UtilisateurDto(utilisateur);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(utilisateur.getId());
        assertThat(result.getNom()).isEqualTo(utilisateur.getNom());
        assertThat(result.getPrenom()).isEqualTo(utilisateur.getPrenom());
        assertThat(result.getTheme()).isEqualTo(utilisateur.getTheme().name());
        assertThat(result.getImageUrl()).isEqualTo(utilisateur.getImageUrl());
    }

    @Test
    void toDomainEntity() {
        UtilisateurDto utilisateur = new UtilisateurDto();
        utilisateur.setId(1L);
        utilisateur.setTheme(Theme.SOMBRE.name());
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");
        utilisateur.setImageUrl("https://google.com");

        Utilisateur result = utilisateur.toDomain();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(utilisateur.getId());
        assertThat(result.getNom()).isEqualTo(utilisateur.getNom());
        assertThat(result.getPrenom()).isEqualTo(utilisateur.getPrenom());
        assertThat(result.getTheme().name()).isEqualTo(utilisateur.getTheme());
        assertThat(result.getImageUrl()).isEqualTo(utilisateur.getImageUrl());
    }
}
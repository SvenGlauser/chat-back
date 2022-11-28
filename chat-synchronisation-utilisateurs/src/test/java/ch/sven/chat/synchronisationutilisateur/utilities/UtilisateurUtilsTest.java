package ch.sven.chat.synchronisationutilisateur.utilities;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;

import static org.assertj.core.api.Assertions.assertThat;

class UtilisateurUtilsTest {

    @Test
    void synchroniser() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId("UUID 1");
        userRepresentation.setFirstName("Prénom");
        userRepresentation.setLastName("Nom");
        userRepresentation.setEmail("email@test.ch");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setKeycloakId("UUID 1");
        utilisateur.setPrenom("Ancien prénom");
        utilisateur.setNom("Ancien nom");
        utilisateur.setEmail("ancien.email@test.ch");
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setImageUrl("http://google.ch");

        Utilisateur result = UtilisateurUtils.synchroniser(null, userRepresentation);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull();
        assertThat(result.getKeycloakId()).isEqualTo("UUID 1");
        assertThat(result.getPrenom()).isEqualTo("Prénom");
        assertThat(result.getNom()).isEqualTo("Nom");
        assertThat(result.getEmail()).isEqualTo("email@test.ch");
        assertThat(result.getTheme()).isEqualTo(Theme.SOMBRE);
        assertThat(result.getImageUrl()).isEqualTo("http://google.com");

        result = UtilisateurUtils.synchroniser(utilisateur, userRepresentation);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getKeycloakId()).isEqualTo("UUID 1");
        assertThat(result.getPrenom()).isEqualTo("Prénom");
        assertThat(result.getNom()).isEqualTo("Nom");
        assertThat(result.getEmail()).isEqualTo("email@test.ch");
        assertThat(result.getTheme()).isEqualTo(Theme.SOMBRE);
        assertThat(result.getImageUrl()).isEqualTo("http://google.ch");
    }
}
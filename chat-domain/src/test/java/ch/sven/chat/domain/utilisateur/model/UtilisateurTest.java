package ch.sven.chat.domain.utilisateur.model;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UtilisateurTest {

    @Test
    void valider() {
        Utilisateur user = new Utilisateur();

        ExceptionTestUtils.assertCoherenceThrownErrorList(
                user::valider,
                Utilisateur.ERROR_KEYCLOAK_ID_OBLIGATOIRE,
                Utilisateur.ERROR_NOM_OBLIGATOIRE,
                Utilisateur.ERROR_PRENOM_OBLIGATOIRE,
                Utilisateur.ERROR_EMAIL_OBLIGATOIRE,
                Utilisateur.ERROR_IMAGE_URL_OBLIGATOIRE,
                Utilisateur.ERROR_THEME_OBLIGATOIRE
        );
    }

    @Test
    void modifyFields() {
        Utilisateur user = new Utilisateur();
        Utilisateur newUser = new Utilisateur();
        newUser.setNom("Nom");
        newUser.setPrenom("Prénom");
        newUser.setEmail("test@test.test");
        newUser.setTheme(Theme.SOMBRE);
        newUser.setImageUrl("https://google.com");
        newUser.setKeycloakId(UUID.randomUUID().toString());
        user.modifyFields(newUser);

        assertThat(user.getNom()).isNull();
        assertThat(user.getPrenom()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getImageUrl()).isEqualTo("https://google.com");
        assertThat(user.getTheme()).isEqualTo(Theme.SOMBRE);
    }
}
package ch.sven.chat.domain.utilisateur;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UtilisateurTest {

    @Test
    void valider() {
        Utilisateur user = new Utilisateur();

        ExceptionTestUtils.assertCoherenceThrownErrorList(
                user::valider,
                Utilisateur.ERROR_NOM_OBLIGATOIRE,
                Utilisateur.ERROR_PRENOM_OBLIGATOIRE,
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
        newUser.setTheme(Theme.SOMBRE);
        newUser.setImageUrl("https://google.com");
        user.modifyFields(newUser);

        assertThat(user.getNom()).isEqualTo("Nom");
        assertThat(user.getPrenom()).isEqualTo("Prénom");
        assertThat(user.getImageUrl()).isEqualTo("https://google.com");
        assertThat(user.getTheme()).isEqualTo(Theme.SOMBRE);
    }
}
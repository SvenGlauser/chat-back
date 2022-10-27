package ch.sven.chat.domain.utilisateur;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ErrorField;
import ch.sven.chat.domain.validation.ValidationMethods;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    @Test
    void valider() {
        Utilisateur user = new Utilisateur();

        assertThatThrownBy(user::valider)
                .isInstanceOf(CoherenceException.class)
                .extracting(exception -> ((CoherenceException) exception).getErrors().stream().map(ErrorField::getMessage).collect(Collectors.toList()), ITERABLE)
                .containsOnly(
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
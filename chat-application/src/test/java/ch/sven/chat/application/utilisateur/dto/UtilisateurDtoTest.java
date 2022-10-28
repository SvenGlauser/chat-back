package ch.sven.chat.application.utilisateur.dto;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurDtoTest {

    @Test
    void utilisateurDto() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setTheme(Theme.SOMBRE);
    }

    @Test
    void toDomainEntity() {
    }
}
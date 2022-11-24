package ch.sven.chat.userssynchronization.utilities;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UtilisateurUtils {
    public static Utilisateur synchroniser(Utilisateur utilisateur, UserRepresentation userRepresentation) {
        if (Objects.isNull(utilisateur)) {
            utilisateur = new Utilisateur();
            utilisateur.setTheme(Theme.SOMBRE);
            utilisateur.setImageUrl("http://google.com");
            utilisateur.setKeycloakId(userRepresentation.getId());
        }
        // Commons information
        utilisateur.setNom(userRepresentation.getLastName());
        utilisateur.setPrenom(userRepresentation.getFirstName());
        utilisateur.setEmail(userRepresentation.getEmail());
        return utilisateur;
    }
}

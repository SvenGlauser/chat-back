package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UtilisateurSynchronizationProcessor implements ItemProcessor<UserRepresentation, UtilisateurEntity> {
    private final UtilisateurRepositoy utilisateurRepositoy;

    @Override
    public UtilisateurEntity process(UserRepresentation userRepresentation) {
        Utilisateur utilisateur = utilisateurRepositoy.lireIdKeycloak(userRepresentation.getId());
        if (Objects.isNull(utilisateur)) {
            utilisateur = new Utilisateur();
            utilisateur.setTheme(Theme.SOMBRE);
            utilisateur.setImageUrl("http://google.com");
            utilisateur.setKeycloakId(userRepresentation.getId());
        }
        // Commons informations
        utilisateur.setNom(userRepresentation.getLastName());
        utilisateur.setPrenom(userRepresentation.getFirstName());
        utilisateur.setEmail(userRepresentation.getEmail());
        utilisateur.valider();
        return new UtilisateurEntity(utilisateur);
    }
}

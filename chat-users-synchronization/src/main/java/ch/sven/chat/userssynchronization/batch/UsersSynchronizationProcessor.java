package ch.sven.chat.userssynchronization.batch;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.infrastructure.hibernate.utilisateur.UtilisateurRepositoryHibernate;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersSynchronizationProcessor implements ItemProcessor<UserRepresentation, UtilisateurEntity> {
    private final UtilisateurRepositoryHibernate utilisateurRepositoy;

    @Override
    public UtilisateurEntity process(UserRepresentation userRepresentation) {
        Optional<UtilisateurEntity> utilisateurEntityOptional = utilisateurRepositoy.findByKeycloakId(userRepresentation.getId());
        UtilisateurEntity utilisateur;
        if (utilisateurEntityOptional.isPresent()) {
            utilisateur = utilisateurEntityOptional.get();
        } else {
            utilisateur = new UtilisateurEntity();
            utilisateur.setTheme(Theme.SOMBRE);
            utilisateur.setImageUrl("http://google.com");
            utilisateur.setKeycloakId(userRepresentation.getId());
        }
        // Commons informations
        utilisateur.setNom(userRepresentation.getLastName());
        utilisateur.setPrenom(userRepresentation.getFirstName());
        utilisateur.setEmail(userRepresentation.getEmail());
        return utilisateur;
    }
}

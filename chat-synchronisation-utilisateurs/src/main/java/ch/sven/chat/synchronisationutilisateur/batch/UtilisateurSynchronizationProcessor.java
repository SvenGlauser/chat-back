package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import ch.sven.chat.synchronisationutilisateur.utilities.UtilisateurUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilisateurSynchronizationProcessor implements ItemProcessor<UserRepresentation, UtilisateurEntity> {
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UtilisateurEntity process(UserRepresentation userRepresentation) {
        Utilisateur utilisateur = utilisateurRepository.lireIdKeycloak(userRepresentation.getId());
        UtilisateurUtils.synchroniser(utilisateur, userRepresentation);
        utilisateur.valider();
        return new UtilisateurEntity(utilisateur);
    }
}

package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.synchronisationutilisateur.utilities.UtilisateurUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilisateurSynchronisationProcessor implements ItemProcessor<UserRepresentation, Utilisateur> {
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur process(UserRepresentation userRepresentation) {
        Utilisateur utilisateur = utilisateurRepository.lireIdKeycloak(userRepresentation.getId());
        utilisateur = UtilisateurUtils.synchroniser(utilisateur, userRepresentation);
        utilisateur.valider();
        return utilisateur;
    }
}

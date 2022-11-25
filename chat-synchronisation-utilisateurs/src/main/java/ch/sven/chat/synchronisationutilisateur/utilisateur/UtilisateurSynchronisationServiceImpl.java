package ch.sven.chat.synchronisationutilisateur.utilisateur;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import ch.sven.chat.synchronisationutilisateur.keycloak.KeycloakAdminImpl;
import ch.sven.chat.synchronisationutilisateur.utilities.UtilisateurUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UtilisateurSynchronisationServiceImpl implements UtilisateurSynchronisationService {

    private final UtilisateurRepositoy utilisateurRepositoy;
    private final KeycloakAdminImpl keycloakAdmin;

    @Override
    public void synchroniser(String idKeycloak) {
        UserRepresentation user = keycloakAdmin.getUtilisateur(idKeycloak);
        if (Objects.isNull(user)) {
            return;
        }

        Utilisateur utilisateur = utilisateurRepositoy.lireIdKeycloak(idKeycloak);
        utilisateur = UtilisateurUtils.synchroniser(utilisateur, user);
        utilisateur.valider();

        if (Objects.isNull(utilisateur.getId())) {
            utilisateurRepositoy.creer(utilisateur);
        } else {
            utilisateurRepositoy.modifier(utilisateur);
        }
    }
}

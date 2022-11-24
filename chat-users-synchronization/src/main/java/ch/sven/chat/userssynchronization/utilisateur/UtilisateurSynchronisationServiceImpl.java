package ch.sven.chat.userssynchronization.utilisateur;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import ch.sven.chat.userssynchronization.keycloak.KeycloakAdminImpl;
import ch.sven.chat.userssynchronization.utilities.UtilisateurUtils;
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
        UserRepresentation user = keycloakAdmin.getUser(idKeycloak);
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

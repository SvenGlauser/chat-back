package ch.sven.chat.synchronisationutilisateur.utilisateur;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.domain.validation.Validation;
import ch.sven.chat.synchronisationutilisateur.keycloak.KeycloakAdmin;
import ch.sven.chat.synchronisationutilisateur.utilities.UtilisateurUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UtilisateurSynchronisationServiceImpl implements UtilisateurSynchronisationService {
    public static final String ERROR_ID_KEYCLOAK_OBLIGATOIRE = "L'id de Keycloak est obligatoire";

    private static final String FIELD_ID_KEYCLOAK = "idKeylcoak";

    private final UtilisateurRepository utilisateurRepository;
    private final KeycloakAdmin keycloakAdmin;

    @Override
    public boolean synchroniser(String idKeycloak) {
        Validation.of(this.getClass())
                .notNull(idKeycloak, FIELD_ID_KEYCLOAK, ERROR_ID_KEYCLOAK_OBLIGATOIRE)
                .validate();

        UserRepresentation user = keycloakAdmin.getUtilisateur(idKeycloak);
        if (Objects.isNull(user)) {
            return false;
        }

        Utilisateur utilisateur = utilisateurRepository.lireIdKeycloak(idKeycloak);
        utilisateur = UtilisateurUtils.synchroniser(utilisateur, user);
        utilisateur.valider();

        if (Objects.isNull(utilisateur.getId())) {
            utilisateurRepository.creer(utilisateur);
        } else {
            utilisateurRepository.modifier(utilisateur);
        }
        return true;
    }
}

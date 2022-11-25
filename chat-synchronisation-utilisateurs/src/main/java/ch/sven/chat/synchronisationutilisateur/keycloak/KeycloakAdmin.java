package ch.sven.chat.synchronisationutilisateur.keycloak;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakAdmin {

    /**
     * Récupérer la liste des utilisateur Keycloak
     * @return Une liste de représentation d'utilisateur
     */
    List<UserRepresentation> getUtilisateurs();

    /**
     * Récupérer un utilisateur Keycloak
     * @param id Id de l'utilisateur
     * @return L'utilisateur
     */
    UserRepresentation getUtilisateur(String id);
}

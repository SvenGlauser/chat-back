package ch.sven.chat.userssynchronization.keycloak;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakAdmin {

    /**
     * Récupérer la liste des utilisateur Keycloak
     * @return Une liste de représentation d'utilisateur
     */
    List<UserRepresentation> getUsers();

    /**
     * Récupérer un utilisateur Keycloak
     * @param id Id de l'utilisateur
     * @return L'utilisateur
     */
    UserRepresentation getUser(String id);
}

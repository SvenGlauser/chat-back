package ch.sven.chat.userssynchronization.keycloak;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakAdmin {

    /**
     * Récupérer la liste des utilisateur Keycloak
     * @return Une liste de représentation d'utilisateur
     */
    List<UserRepresentation> getUsers();
}

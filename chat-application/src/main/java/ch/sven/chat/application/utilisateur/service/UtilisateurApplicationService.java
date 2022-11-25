package ch.sven.chat.application.utilisateur.service;

import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;

/**
 * Service applicatif permettant la gestion des utilisateurs
 */
public interface UtilisateurApplicationService {

    /**
     * Lire un utilisateur
     * @param id Id de l'utilisateur
     * @return L'utilisateur
     */
    UtilisateurDto lire(Long id);

    /**
     * Modifier un utilisateur
     * @param utilisateur Utilisateur à modifier
     * @return Utilisateur modifié
     */
    UtilisateurDto modifier(UtilisateurDto utilisateur);

    /**
     * Synchroniser un utilisateur
     * @param idKeycloak Id Keycloak de l'utilisateur à synchroniser
     * @return True si la synchronisation s'est déroulée correctement
     */
    boolean synchroniser(String idKeycloak);
}

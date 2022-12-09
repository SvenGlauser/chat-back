package ch.sven.chat.domain.utilisateur.service;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;

/**
 * Service permettant la gestion des utilisateurs
 */
public interface UtilisateurService {

    /**
     * Lire un utilisateur
     * @param id Id de l'utilisateur
     * @return L'utilisateur
     */
    Utilisateur lire(Long id);

    /**
     * Lire un utilisateur
     * @param idKeycloak Id Keycloak de l'utilisateur
     * @return L'utilisateur
     */
    Utilisateur lireIdKeycloak(String idKeycloak);

    /**
     * Modifier un utilisateur
     * @param utilisateur Utilisateur à modifier
     * @return Utilisateur modifié
     */
    Utilisateur modifier(Utilisateur utilisateur);
}

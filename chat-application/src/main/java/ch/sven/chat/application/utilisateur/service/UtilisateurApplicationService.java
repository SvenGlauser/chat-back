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
     * Créer un nouvel utilisateur
     * @param utilisateur Utilisateur à créer
     * @return Utilisateur créé
     */
    UtilisateurDto creer(UtilisateurDto utilisateur);

    /**
     * Modifier un utilisateur
     * @param utilisateur Utilisateur à modifier
     * @return Utilisateur modifié
     */
    UtilisateurDto modifier(UtilisateurDto utilisateur);
}

package ch.sven.chat.domain.utilisateur.repository;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;

public interface UtilisateurRepositoy {

    /**
     * Lire un utilisateur
     * @param id Id de l'utilisateur
     * @return L'utilisateur
     */
    Utilisateur lire(Long id);

    /**
     * Modifier un utilisateur
     * @param utilisateur Utilisateur à modifier
     * @return Utilisateur modifié
     */
    Utilisateur modifier(Utilisateur utilisateur);
}

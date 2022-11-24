package ch.sven.chat.userssynchronization.utilisateur;

/**
 * Service permettant la synchronisation manuelle des utilisateurs
 */
public interface UtilisateurSynchronisationService {
    /**
     * Synchroniser un utilisateur
     * @param idKeycloak Id keycloak à synchroniser
     */
    void synchroniser(String idKeycloak);
}

package ch.sven.chat.synchronisationutilisateur.utilisateur;

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

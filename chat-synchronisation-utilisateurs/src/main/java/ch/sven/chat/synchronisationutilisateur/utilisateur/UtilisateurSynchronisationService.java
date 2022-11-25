package ch.sven.chat.synchronisationutilisateur.utilisateur;

/**
 * Service permettant la synchronisation manuelle des utilisateurs
 */
public interface UtilisateurSynchronisationService {
    /**
     * Synchroniser un utilisateur
     * @param idKeycloak Id keycloak à synchroniser
     * @return True si la synchronisation s'est déroulée correctement
     */
    boolean synchroniser(String idKeycloak);
}

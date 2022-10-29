package ch.sven.chat.domain.message.repository;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.model.Message;

/**
 * Repository de gestion des messages
 */
public interface MessageRepository {
    /**
     * Lire un message
     * @param id Id du message
     * @return Le message
     */
    Message lire(Long id);

    /**
     * Rechercher une liste de messages
     * @param messageSearchQuery Les éléments de recherche
     * @return Les messages
     */
    SearchResult<Message> rechercher(MessageSearchQuery messageSearchQuery);

    /**
     * Envoyer un message
     * @param message Message à envoyer
     * @return Message envoyé
     */
    Message envoyer(Message message);

    /**
     * Modifier un message
     * @param message Message à modifier
     * @return Message modifié
     */
    Message modifier(Message message);

    /**
     * Supprimer un message
     * @param id Id du message à supprimer
     */
    void supprimer(Long id);
}

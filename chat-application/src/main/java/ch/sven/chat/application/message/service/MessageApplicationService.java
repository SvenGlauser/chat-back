package ch.sven.chat.application.message.service;

import ch.sven.chat.application.message.dto.MessageDto;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;

/**
 * Service applicatif de gestion des messages
 */
public interface MessageApplicationService {
    /**
     * Lire un message
     * @param id Id du message
     * @return Le message
     */
    MessageDto lire(Long id);

    /**
     * Rechercher une liste de messages
     *
     * @param messageSearchQuery Les éléments de recherche
     * @return Les messages
     */
    SearchResult<MessageDto> rechercher(MessageSearchQuery messageSearchQuery);

    /**
     * Envoyer un message
     * @param message Message à envoyer
     * @return Message envoyé
     */
    MessageDto envoyer(MessageDto message);

    /**
     * Modifier un message
     * @param message Message à modifier
     * @return Message modifié
     */
    MessageDto modifier(MessageDto message);

    /**
     * Supprimer un message
     * @param id Id du message à supprimer
     */
    void supprimer(Long id);
}

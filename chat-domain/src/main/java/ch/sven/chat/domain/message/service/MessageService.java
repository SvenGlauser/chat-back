package ch.sven.chat.domain.message.service;

import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;

import java.util.List;

/**
 * Service de gestion des messages
 */
public interface MessageService {
    Message lire(Long id);
    List<Message> rechercher(MessageSearchQuery messageSearchQuery);
    Message envoyer(Message message);
    Message modifier(Message message);
    void supprimer(Long id);
}

package ch.sven.chat.domain.message.service;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.message.repository.MessageRepository;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.domain.validation.Validation;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * Implémentation du service de gestion des messages
 */
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du message est obligatoire";
    public static final String ERROR_MESSAGE_OBLIGATOIRE = "Le message est obligatoire";
    public static final String ERROR_MESSAGE_NON_TROUVE = "Le message à modifier n'a pas été trouvé";
    public static final String ERROR_SEARCH_QUERY_OBLIGATOIRE = "La search query est obligatoire";
    private static final String FIELD_ID = "id";
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_SEARCH_QUERY = "searchQuery";

    private final MessageRepository messageRepository;

    @Override
    public Message lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return messageRepository.lire(id);
    }

    @Override
    public List<Message> rechercher(MessageSearchQuery searchQuery) {
        Validation.of(this.getClass())
                .notNull(searchQuery, FIELD_SEARCH_QUERY, ERROR_SEARCH_QUERY_OBLIGATOIRE)
                .validate();

        searchQuery.valider();

        return messageRepository.rechercher(searchQuery);
    }

    @Override
    public Message envoyer(Message message) {
        Validation.of(this.getClass())
                .notNull(message, FIELD_MESSAGE, ERROR_MESSAGE_OBLIGATOIRE)
                .validate();

        message.setId(null);
        message.valider();

        return messageRepository.envoyer(message);
    }

    @Override
    public Message modifier(Message message) {
        Validation.of(this.getClass())
                .notNull(message, FIELD_MESSAGE, ERROR_MESSAGE_OBLIGATOIRE)
                .validate();

        Message oldMessage = messageRepository.lire(message.getId());
        if (Objects.isNull(oldMessage)) {
            throw new CoherenceException(this.getClass().getSimpleName(), ERROR_MESSAGE_NON_TROUVE);
        }

        oldMessage.modifyFields(message).valider();

        return messageRepository.modifier(oldMessage);
    }

    @Override
    public void supprimer(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        messageRepository.lire(id);
    }
}

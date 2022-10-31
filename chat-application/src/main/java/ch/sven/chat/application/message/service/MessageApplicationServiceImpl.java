package ch.sven.chat.application.message.service;

import ch.sven.chat.application.message.dto.MessageDto;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.domain.message.service.MessageService;
import ch.sven.chat.domain.validation.Validation;
import ch.sven.chat.infrastructure.utils.search.SearchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageApplicationServiceImpl implements MessageApplicationService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du message est obligatoire";
    public static final String ERROR_MESSAGE_OBLIGATOIRE = "Le message est obligatoire";
    public static final String ERROR_SEARCH_QUERY_OBLIGATOIRE = "La search query est obligatoire";
    private static final String FIELD_ID = "id";
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_SEARCH_QUERY = "searchQuery";

    private final MessageService messageService;

    @Override
    public MessageDto lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(messageService.lire(id)).map(MessageDto::new).orElse(null);
    }

    @Override
    public SearchResult<MessageDto> rechercher(MessageSearchQuery searchQuery) {
        Validation.of(this.getClass())
                .notNull(searchQuery, FIELD_SEARCH_QUERY, ERROR_SEARCH_QUERY_OBLIGATOIRE)
                .validate();

        return SearchUtils.transform(messageService.rechercher(searchQuery), MessageDto::new);
    }

    @Override
    @Transactional
    public MessageDto envoyer(MessageDto message) {
        Validation.of(this.getClass())
                .notNull(message, FIELD_MESSAGE, ERROR_MESSAGE_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(messageService.envoyer(message.toDomain())).map(MessageDto::new).orElse(null);
    }

    @Override
    @Transactional
    public MessageDto modifier(MessageDto message) {
        Validation.of(this.getClass())
                .notNull(message, FIELD_MESSAGE, ERROR_MESSAGE_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(messageService.modifier(message.toDomain())).map(MessageDto::new).orElse(null);
    }

    @Override
    @Transactional
    public void supprimer(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        messageService.supprimer(id);
    }
}

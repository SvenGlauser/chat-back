package ch.sven.chat.infrastructure.message.repository;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.message.repository.MessageRepository;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.infrastructure.common.Dao;
import ch.sven.chat.infrastructure.hibernate.message.MessageRepositoryHibernate;
import ch.sven.chat.infrastructure.message.entity.MessageEntity;
import ch.sven.chat.infrastructure.utils.search.SearchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {
    private final MessageRepositoryHibernate messageRepositoryHibernate;

    @Override
    public Message lire(Long id) {
        return messageRepositoryHibernate.findById(id).map(Dao::toDomain).orElse(null);
    }

    @Override
    public SearchResult<Message> rechercher(MessageSearchQuery messageSearchQuery) {
        return SearchUtils.transform(messageRepositoryHibernate.findAll(messageSearchQuery), MessageEntity::toDomain);
    }

    @Override
    public Message envoyer(Message message) {
        return Optional
                .of(messageRepositoryHibernate.save(new MessageEntity(message)))
                .map(Dao::toDomain)
                .orElse(null);
    }

    @Override
    public Message modifier(Message message) {
        return Optional
                .of(messageRepositoryHibernate.save(new MessageEntity(message)))
                .map(Dao::toDomain)
                .orElse(null);
    }

    @Override
    public void supprimer(Long id) {
        messageRepositoryHibernate.deleteById(id);
    }
}

package ch.sven.chat.infrastructure.hibernate.message;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.infrastructure.message.entity.MessageEntity;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface MessageRepositoryHibernate extends JpaRepository<MessageEntity, Long>, JpaSpecificationExecutor<MessageEntity> {

    /**
     * Recherche tous les messages
     * @param searchQuery Éléments de recherche
     * @return Une liste de messages
     */
    default SearchResult<MessageEntity> findAll(MessageSearchQuery searchQuery) {
        return SearchResult.of(this.findAll(getCriteriaBuilder(searchQuery)));
    }

    /**
     * Créer les critères de recherche
     * @param searchQuery Éléments de recherche pour les messages
     * @return Une specification de {@link MessageEntity}
     */
    private static Specification<MessageEntity> getCriteriaBuilder(MessageSearchQuery searchQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<MessageEntity, UtilisateurEntity> emetteur = root.join("emetteur");
            Join<MessageEntity, UtilisateurEntity> destinataire = root.join("destinataire");

            predicates.add(criteriaBuilder.equal(emetteur.get("id"), searchQuery.getIdEmetteur()));
            predicates.add(criteriaBuilder.equal(destinataire.get("id"), searchQuery.getIdDestinataire()));

            if (Objects.nonNull(searchQuery.getAfter())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("modification"), searchQuery.getAfter()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

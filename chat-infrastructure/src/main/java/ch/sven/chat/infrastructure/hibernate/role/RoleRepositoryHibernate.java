package ch.sven.chat.infrastructure.hibernate.role;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import ch.sven.chat.infrastructure.role.entity.RoleEntity;
import ch.sven.chat.infrastructure.utils.search.SearchUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface RoleRepositoryHibernate extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {

    /**
     * Recherche tous les rôles
     * @param searchQuery Éléments de recherche
     * @return Une liste de rôles
     */
    default SearchResult<RoleEntity> findAll(RoleSearchQuery searchQuery) {
        return SearchUtils.of(this.findAll(getCriteriaBuilder(searchQuery), SearchUtils.getPageRequest(searchQuery)));
    }

    /**
     * Créer les critères de recherche
     * @param searchQuery Éléments de recherche pour les rôles
     * @return Une specification de {@link RoleEntity}
     */
    private static Specification<RoleEntity> getCriteriaBuilder(RoleSearchQuery searchQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(searchQuery.getNom())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nom")), "%" + searchQuery.getNom().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
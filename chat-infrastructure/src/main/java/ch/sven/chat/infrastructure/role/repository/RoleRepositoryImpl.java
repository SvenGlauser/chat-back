package ch.sven.chat.infrastructure.role.repository;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.model.Role;
import ch.sven.chat.domain.role.repository.RoleRepository;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import ch.sven.chat.infrastructure.common.Dao;
import ch.sven.chat.infrastructure.hibernate.role.RoleRepositoryHibernate;
import ch.sven.chat.infrastructure.role.entity.RoleEntity;
import ch.sven.chat.infrastructure.utils.search.SearchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleRepositoryHibernate roleRepositoryHibernate;

    @Override
    public Role lire(Long id) {
        return roleRepositoryHibernate.findById(id).map(Dao::toDomain).orElse(null);
    }

    @Override
    public Role modifier(Role role) {
        return Optional
                .of(roleRepositoryHibernate.save(new RoleEntity(role)))
                .map(Dao::toDomain)
                .orElse(null);
    }

    @Override
    public SearchResult<Role> rechercher(RoleSearchQuery searchQuery) {
        return SearchUtils.transform(roleRepositoryHibernate.findAll(searchQuery), Dao::toDomain);
    }
}

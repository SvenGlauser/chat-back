package ch.sven.chat.domain.role.service;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.role.model.Role;
import ch.sven.chat.domain.role.repository.RoleRepository;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import ch.sven.chat.domain.validation.Validation;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du rôle est obligatoire";
    public static final String ERROR_ROLE_OBLIGATOIRE = "Le role est obligatoire";
    public static final String ERROR_SEARCH_QUERY_OBLIGATOIRE = "La search query est obligatoire";
    public static final String ERROR_ROLE_NON_TROUVE = "Le rôle n'a pas été trouvé";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ROLE = "role";
    private static final String FIELD_SEARCH_QUERY = "searchQuery";

    private final RoleRepository roleRepository;

    @Override
    public Role lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return roleRepository.lire(id);
    }

    @Override
    public Role modifier(Role role) {
        Validation.of(this.getClass())
                .notNull(role, FIELD_ROLE, ERROR_ROLE_OBLIGATOIRE)
                .validate();

        Role oldRole = roleRepository.lire(role.getId());
        if (Objects.isNull(oldRole)) {
            throw new CoherenceException(this.getClass().getSimpleName(), ERROR_ROLE_NON_TROUVE);
        }

        oldRole.modifyFields(role).valider();

        return roleRepository.modifier(role);
    }

    @Override
    public SearchResult<Role> rechercher(RoleSearchQuery searchQuery) {
        Validation.of(this.getClass())
                .notNull(searchQuery, FIELD_SEARCH_QUERY, ERROR_SEARCH_QUERY_OBLIGATOIRE)
                .validate();

        return roleRepository.rechercher(searchQuery);
    }
}

package ch.sven.chat.application.role.service;

import ch.sven.chat.application.role.dto.RoleDto;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import ch.sven.chat.domain.role.service.RoleService;
import ch.sven.chat.domain.validation.Validation;
import ch.sven.chat.infrastructure.utils.search.SearchUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleApplicationServiceImpl implements RoleApplicationService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du rôle est obligatoire";
    public static final String ERROR_ROLE_OBLIGATOIRE = "Le role est obligatoire";
    public static final String ERROR_SEARCH_QUERY_OBLIGATOIRE = "La search query est obligatoire";
    public static final String ERROR_ROLE_NON_TROUVE = "Le rôle n'a pas été trouvé";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ROLE = "role";
    private static final String FIELD_SEARCH_QUERY = "searchQuery";

    private final RoleService roleService;

    @Override
    public RoleDto lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(roleService.lire(id)).map(RoleDto::new).orElse(null);
    }

    @Override
    @Transactional
    public RoleDto modifier(RoleDto role) {
        Validation.of(this.getClass())
                .notNull(role, FIELD_ROLE, ERROR_ROLE_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(roleService.modifier(role.toDomain())).map(RoleDto::new).orElse(null);
    }

    @Override
    public SearchResult<RoleDto> rechercher(RoleSearchQuery searchQuery) {
        Validation.of(this.getClass())
                .notNull(searchQuery, FIELD_SEARCH_QUERY, ERROR_SEARCH_QUERY_OBLIGATOIRE)
                .validate();

        return SearchUtils.transform(roleService.rechercher(searchQuery), RoleDto::new);
    }
}

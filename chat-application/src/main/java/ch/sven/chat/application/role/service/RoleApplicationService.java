package ch.sven.chat.application.role.service;

import ch.sven.chat.application.role.dto.RoleDto;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;

/**
 * Service de gestion des rôles
 */
public interface RoleApplicationService {

    /**
     * Lire un rôle
     * @param id Id du rôle
     * @return Un rôle
     */
    RoleDto lire(Long id);

    /**
     * Modifier un rôle
     * @param role Rôle à modifier
     * @return Rôle modifié
     */
    RoleDto modifier(RoleDto role);

    /**
     * Rechercher des rôles
     * @param searchQuery Éléments de recherche
     * @return Une liste de rôles
     */
    SearchResult<RoleDto> rechercher(RoleSearchQuery searchQuery);
}

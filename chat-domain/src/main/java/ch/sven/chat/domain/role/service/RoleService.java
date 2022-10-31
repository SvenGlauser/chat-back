package ch.sven.chat.domain.role.service;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.model.Role;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;

/**
 * Service de gestion des rôles
 */
public interface RoleService {

    /**
     * Lire un rôle
     * @param id Id du rôle
     * @return Un rôle
     */
    Role lire(Long id);

    /**
     * Modifier un rôle
     * @param role Rôle à modifier
     * @return Rôle modifié
     */
    Role modifier(Role role);

    /**
     * Rechercher des rôles
     * @param searchQuery Éléments de recherche
     * @return Une liste de rôles
     */
    SearchResult<Role> rechercher(RoleSearchQuery searchQuery);
}

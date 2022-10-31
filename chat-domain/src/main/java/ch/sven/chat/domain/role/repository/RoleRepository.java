package ch.sven.chat.domain.role.repository;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.model.Role;

/**
 * Repository de gestion des rôles
 */
public interface RoleRepository {

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

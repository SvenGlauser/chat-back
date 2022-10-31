package ch.sven.chat.domain.role.service;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.exception.ExceptionTestUtils;
import ch.sven.chat.domain.role.model.Role;
import ch.sven.chat.domain.role.repository.RoleRepository;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @InjectMocks
    RoleServiceImpl roleService;

    @Mock
    RoleRepository roleRepository;

    @Test
    void lire() {
        Role role = generateRole();
        role.setId(1L);

        Mockito.when(roleRepository.lire(Mockito.anyLong())).thenReturn(role);
        Role result = roleService.lire(role.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> roleService.lire(null), RoleServiceImpl.ERROR_ID_OBLIGATOIRE);
    }

    @Test
    void modifier() {
        Role role = generateRole();
        role.setId(1L);

        Mockito.when(roleRepository.lire(Mockito.anyLong())).thenReturn(role);
        Mockito.when(roleRepository.modifier(Mockito.any(Role.class))).thenReturn(role);
        Role result = roleService.modifier(role);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> roleService.modifier(null), RoleServiceImpl.ERROR_ROLE_OBLIGATOIRE);

        Mockito.when(roleRepository.lire(Mockito.anyLong())).thenReturn(null);
        ExceptionTestUtils.assertCoherenceThrownError(() -> roleService.modifier(role), RoleServiceImpl.ERROR_ROLE_NON_TROUVE);
    }

    @Test
    void rechercher() {
        Role role = generateRole();
        role.setId(1L);

        RoleSearchQuery searchQuery = new RoleSearchQuery();
        searchQuery.setNom("Nom");

        Mockito.when(roleRepository.rechercher(Mockito.any(RoleSearchQuery.class))).thenReturn(SearchResult.of(List.of(role)));
        SearchResult<Role> result = roleService.rechercher(searchQuery);

        Assertions.assertThat(result.getTotalElements()).isPositive();
        assertThat(result.getElements().get(0)).isEqualTo(role);

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> roleService.rechercher(null), RoleServiceImpl.ERROR_SEARCH_QUERY_OBLIGATOIRE);
    }

    private static Role generateRole() {
        Role role = new Role();
        role.setNom("Nom");
        return role;
    }
}
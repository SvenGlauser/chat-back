package ch.sven.chat.application.role.service;

import ch.sven.chat.application.exception.ExceptionTestUtils;
import ch.sven.chat.application.role.dto.RoleDto;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import ch.sven.chat.infrastructure.hibernate.role.RoleRepositoryHibernate;
import ch.sven.chat.infrastructure.role.entity.RoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoleApplicationServiceImplTest {

    @Autowired
    RoleApplicationServiceImpl roleApplicationService;

    @Autowired
    RoleRepositoryHibernate roleRepositoryHibernate;

    @Test
    void lire() {
        RoleDto role = generateRole();
        role = saveRole(role);

        RoleDto result = roleApplicationService.lire(role.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> roleApplicationService.lire(null), RoleApplicationServiceImpl.ERROR_ID_OBLIGATOIRE);

        result = roleApplicationService.lire(10000L);
        assertThat(result).isNull();
    }

    @Test
    void rechercher() {
        RoleDto role = generateRole();
        saveRole(role);

        RoleSearchQuery searchQuery = new RoleSearchQuery();

        SearchResult<RoleDto> result = roleApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getElements().get(0).getNom()).isEqualTo("Nom");

        searchQuery.setNom("o");
        result = roleApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getElements().get(0).getNom()).isEqualTo("Nom");

        searchQuery.setNom("x");
        result = roleApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isZero();

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> roleApplicationService.rechercher(null), RoleApplicationServiceImpl.ERROR_SEARCH_QUERY_OBLIGATOIRE);
    }

    private RoleDto saveRole(RoleDto role) {
        return new RoleDto(roleRepositoryHibernate.save(new RoleEntity((role.toDomain()))).toDomain());
    }

    @Test
    void modifier() {
        RoleDto role = generateRole();
        role = saveRole(role);

        role.setNom("New nom");
        role = roleApplicationService.modifier(role);

        RoleDto result = roleApplicationService.lire(role.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("New nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> roleApplicationService.modifier(null), RoleApplicationServiceImpl.ERROR_ROLE_OBLIGATOIRE);
    }

    private RoleDto generateRole() {
        RoleDto role = new RoleDto();
        role.setNom("Nom");
        return role;
    }
}
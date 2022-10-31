package ch.sven.chat.application.role.dto;

import ch.sven.chat.domain.role.model.Role;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleDtoTest {

    @Test
    void roleDto() {
        Role role = new Role();
        role.setId(1L);
        role.setNom("Role de test");

        RoleDto result = new RoleDto(role);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Role de test");
    }

    @Test
    void toDomainEntity() {
        RoleDto role = new RoleDto();
        role.setId(1L);
        role.setNom("Role de test");

        Role result = role.toDomain();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Role de test");
    }
}
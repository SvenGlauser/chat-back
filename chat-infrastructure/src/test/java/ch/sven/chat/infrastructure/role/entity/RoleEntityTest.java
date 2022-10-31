package ch.sven.chat.infrastructure.role.entity;

import ch.sven.chat.domain.role.model.Role;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleEntityTest {

    @Test
    void roleEntity() {
        Role role = new Role();
        role.setId(1L);
        role.setNom("Role de test");

        RoleEntity result = new RoleEntity(role);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Role de test");
    }

    @Test
    void toDomainEntity() {
        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setNom("Role de test");

        Role result = role.toDomain();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Role de test");
    }
}
package ch.sven.chat.domain.role.model;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    @Test
    void valider() {
        Role role = new Role();

        ExceptionTestUtils.assertCoherenceThrownErrorList(
                role::valider,
                Role.ERROR_NOM_OBLIGATOIRE
        );
    }

    @Test
    void modifyFields() {
        Role role = new Role();
        role.setNom("OldNom");

        Role newRole = new Role();
        newRole.setNom("Nom");

        role.modifyFields(newRole);

        assertThat(role.getNom()).isEqualTo("Nom");
    }
}
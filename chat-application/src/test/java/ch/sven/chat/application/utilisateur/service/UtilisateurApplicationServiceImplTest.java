package ch.sven.chat.application.utilisateur.service;

import ch.sven.chat.application.exception.ExceptionTestUtils;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.application.utilisateur.generator.UtilisateurGenerator;
import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.security.roles.PermissionsConstantes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static ch.sven.chat.application.configuration.SecurityConfigurationTest.USER_TEST;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UtilisateurApplicationServiceImplTest {

    @Autowired
    UtilisateurApplicationServiceImpl utilisateurApplicationService;

    @Autowired
    UtilisateurGenerator utilisateurGenerator;

    @Test
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
    void lire() {
        UtilisateurDto utilisateurDto = utilisateurGenerator.generateTestUser();

        UtilisateurDto result = utilisateurApplicationService.lire(utilisateurDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("user_test");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.lire(null), UtilisateurApplicationServiceImpl.ERROR_ID_OBLIGATOIRE);

        result = utilisateurApplicationService.lire(10000L);
        assertThat(result).isNull();
    }

    @Test
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
    void lireIdKeycloak() {
        UtilisateurDto utilisateurDto = utilisateurGenerator.generateTestUser();

        UtilisateurDto result = utilisateurApplicationService.lireIdKeycloak(utilisateurDto.getKeycloakId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("user_test");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.lireIdKeycloak(null), UtilisateurApplicationServiceImpl.ERROR_ID_KEYCLOAK_OBLIGATOIRE);

        result = utilisateurApplicationService.lireIdKeycloak("");
        assertThat(result).isNull();
    }

    @Test
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
    void modifier() {
        UtilisateurDto utilisateur = utilisateurGenerator.generateTestUser();
        utilisateur.setNom("Nouveau nom");
        utilisateur.setTheme(Theme.SOMBRE.name());
        utilisateur = utilisateurApplicationService.modifier(utilisateur);

        UtilisateurDto result = utilisateurApplicationService.lire(utilisateur.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("user_test");
        assertThat(result.getTheme()).isEqualTo(Theme.SOMBRE.name());

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.modifier(null), UtilisateurApplicationServiceImpl.ERROR_UTILISATEUR_OBLIGATOIRE);
    }
}
package ch.sven.chat.application.utilisateur.service;

import ch.sven.chat.application.exception.ExceptionTestUtils;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.application.utilisateur.generator.UtilisateurGenerator;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.security.roles.PermissionsConstantes;
import ch.sven.chat.synchronisationutilisateur.keycloak.KeycloakAdmin;
import ch.sven.chat.synchronisationutilisateur.utilisateur.UtilisateurSynchronisationServiceImpl;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static ch.sven.chat.application.configuration.SecurityConfigurationTest.USER_TEST;

@SpringBootTest
class UtilisateurSynchronisationServiceImplTest {

    @InjectMocks
    UtilisateurSynchronisationServiceImpl utilisateurSynchronisationService;

    @Mock
    KeycloakAdmin keycloakAdmin;

    @Spy
    UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurApplicationServiceImpl utilisateurApplicationService;

    @Autowired
    UtilisateurGenerator utilisateurGenerator;

    @Test
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
    void synchroniser() {
        UtilisateurDto utilisateur = utilisateurGenerator.generateTestUser();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(utilisateur.getKeycloakId());
        userRepresentation.setLastName("Nouveau nom");
        userRepresentation.setFirstName("Nouveau prénom");
        userRepresentation.setEmail("nouveau.email@test.ch");

        Mockito.when(keycloakAdmin.getUtilisateur(Mockito.any())).thenReturn(userRepresentation);
        utilisateurSynchronisationService.synchroniser(utilisateur.getKeycloakId());

        UtilisateurDto result = utilisateurApplicationService.lire(utilisateur.getId());

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.synchroniser(null), UtilisateurApplicationServiceImpl.ERROR_ID_KEYCLOAK_OBLIGATOIRE);
    }
}
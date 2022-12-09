package ch.sven.chat.synchronisationutilisateur.utilisateur;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.synchronisationutilisateur.exception.ExceptionTestUtils;
import ch.sven.chat.synchronisationutilisateur.keycloak.KeycloakAdmin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UtilisateurSynchronisationServiceImplTest {

    @InjectMocks
    UtilisateurSynchronisationServiceImpl utilisateurSynchronisationService;

    @Mock
    KeycloakAdmin keycloakAdmin;

    @Mock
    UtilisateurRepository utilisateurRepository;

    @Test
    void synchroniser() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId("UUID 1");
        userRepresentation.setFirstName("Prénom");
        userRepresentation.setLastName("Nom");
        userRepresentation.setEmail("email@test.ch");

        Mockito.when(keycloakAdmin.getUtilisateur(Mockito.any())).thenReturn(null);
        boolean result = utilisateurSynchronisationService.synchroniser("UUID 1");
        assertThat(result).isFalse();

        Mockito.when(keycloakAdmin.getUtilisateur(Mockito.any())).thenReturn(userRepresentation);
        Mockito.when(utilisateurRepository.lireIdKeycloak(Mockito.any())).thenReturn(null);
        Mockito.when(utilisateurRepository.creer(Mockito.any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0, Utilisateur.class));

        result = utilisateurSynchronisationService.synchroniser("UUID 1");
        assertThat(result).isTrue();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setTheme(Theme.SOMBRE);
        utilisateur.setImageUrl("http://google.com");
        utilisateur.setKeycloakId("UUID 1");

        Mockito.when(keycloakAdmin.getUtilisateur(Mockito.any())).thenReturn(userRepresentation);
        Mockito.when(utilisateurRepository.lireIdKeycloak(Mockito.any())).thenReturn(utilisateur);
        Mockito.when(utilisateurRepository.creer(Mockito.any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0, Utilisateur.class));

        result = utilisateurSynchronisationService.synchroniser("UUID 1");
        assertThat(result).isTrue();

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurSynchronisationService.synchroniser(null), UtilisateurSynchronisationServiceImpl.ERROR_ID_KEYCLOAK_OBLIGATOIRE);
    }
}
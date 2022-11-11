package ch.sven.chat.domain.utilisateur.service;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceImplTest {

    @InjectMocks
    UtilisateurServiceImpl utilisateurService;

    @Mock
    UtilisateurRepositoy utilisateurRepositoy;

    @Test
    void lire() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");

        Mockito.when(utilisateurRepositoy.lire(Mockito.anyLong())).thenReturn(utilisateur);
        Utilisateur result = utilisateurService.lire(utilisateur.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurService.lire(null), UtilisateurServiceImpl.ERROR_ID_OBLIGATOIRE);
    }

    @Test
    void modifier() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prenom");
        utilisateur.setImageUrl("https://google.com");
        utilisateur.setTheme(Theme.SOMBRE);

        Mockito.when(utilisateurRepositoy.lire(Mockito.anyLong())).thenReturn(utilisateur);
        Mockito.when(utilisateurRepositoy.modifier(Mockito.any(Utilisateur.class))).thenReturn(utilisateur);
        Utilisateur result = utilisateurService.modifier(utilisateur);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurService.modifier(null), UtilisateurServiceImpl.ERROR_UTILISATEUR_OBLIGATOIRE);

        Mockito.when(utilisateurRepositoy.lire(Mockito.anyLong())).thenReturn(null);
        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurService.modifier(utilisateur), UtilisateurServiceImpl.ERROR_UTILISATEUR_NON_TROUVE);

    }
}
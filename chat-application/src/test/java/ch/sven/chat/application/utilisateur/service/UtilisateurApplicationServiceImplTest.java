package ch.sven.chat.application.utilisateur.service;

import ch.sven.chat.application.exception.ExceptionTestUtils;
import ch.sven.chat.application.message.dto.MessageDto;
import ch.sven.chat.application.message.service.MessageApplicationService;
import ch.sven.chat.application.message.service.MessageApplicationServiceImpl;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.domain.utilisateur.model.Theme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UtilisateurApplicationServiceImplTest {

    @Autowired
    UtilisateurApplicationServiceImpl utilisateurApplicationService;

    @Test
    void lire() {
        UtilisateurDto utilisateurDto = generateUtilisateur();
        utilisateurDto = utilisateurApplicationService.creer(utilisateurDto);

        UtilisateurDto result = utilisateurApplicationService.lire(utilisateurDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.lire(null), UtilisateurApplicationServiceImpl.ERROR_ID_OBLIGATOIRE);

        result = utilisateurApplicationService.lire(10000L);
        assertThat(result).isNull();
    }

    @Test
    void creer() {
        UtilisateurDto utilisateur = generateUtilisateur();
        utilisateur = utilisateurApplicationService.creer(utilisateur);

        UtilisateurDto result = utilisateurApplicationService.lire(utilisateur.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.creer(null), UtilisateurApplicationServiceImpl.ERROR_UTILISATEUR_OBLIGATOIRE);
    }

    @Test
    void modifier() {
        UtilisateurDto utilisateur = generateUtilisateur();
        utilisateur = utilisateurApplicationService.creer(utilisateur);
        utilisateur.setNom("Nouveau nom");
        utilisateur = utilisateurApplicationService.modifier(utilisateur);

        UtilisateurDto result = utilisateurApplicationService.lire(utilisateur.getId());
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Nouveau nom");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> utilisateurApplicationService.modifier(null), UtilisateurApplicationServiceImpl.ERROR_UTILISATEUR_OBLIGATOIRE);
    }

    private UtilisateurDto generateUtilisateur() {
        UtilisateurDto utilisateur = new UtilisateurDto();
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prenom");
        utilisateur.setImageUrl("https://google.com");
        utilisateur.setTheme(Theme.CLAIR.name());
        return utilisateur;
    }
}
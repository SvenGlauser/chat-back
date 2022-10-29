package ch.sven.chat.application.message.service;

import ch.sven.chat.application.exception.ExceptionTestUtils;
import ch.sven.chat.application.message.dto.MessageDto;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.application.utilisateur.service.UtilisateurApplicationService;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageApplicationServiceImplTest {

    @Autowired
    MessageApplicationServiceImpl messageApplicationService;

    @Autowired
    UtilisateurApplicationService utilisateurApplicationService;

    @Test
    void lire() {
        MessageDto message = generateMessage();
        message = messageApplicationService.envoyer(message);

        MessageDto result = messageApplicationService.lire(message.getId());
        assertThat(result).isNotNull();
        assertThat(result.getContenu()).isEqualTo("Mon contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageApplicationService.lire(null), MessageApplicationServiceImpl.ERROR_ID_OBLIGATOIRE);

        result = messageApplicationService.lire(10000L);
        assertThat(result).isNull();
    }

    @Test
    void rechercher() {
        UtilisateurDto destinataire = new UtilisateurDto();
        destinataire.setNom("Destinataire");
        destinataire.setPrenom("Prenom");
        destinataire.setImageUrl("https://google.com");
        destinataire.setTheme(Theme.CLAIR.name());
        destinataire = utilisateurApplicationService.creer(destinataire);

        UtilisateurDto destinataire1 = new UtilisateurDto();
        destinataire1.setNom("Destinataire");
        destinataire1.setPrenom("Prenom");
        destinataire1.setImageUrl("https://google.com");
        destinataire1.setTheme(Theme.CLAIR.name());
        destinataire1 = utilisateurApplicationService.creer(destinataire1);

        UtilisateurDto emetteur = new UtilisateurDto();
        emetteur.setNom("Émetteur");
        emetteur.setPrenom("Prenom");
        emetteur.setImageUrl("https://google.com");
        emetteur.setTheme(Theme.CLAIR.name());
        emetteur = utilisateurApplicationService.creer(emetteur);

        MessageDto message = generateMessage(destinataire, emetteur);
        message.setContenu("1");
        messageApplicationService.envoyer(message);
        MessageDto message1 = generateMessage(destinataire1, emetteur);
        message1.setContenu("2");
        messageApplicationService.envoyer(message1);

        MessageSearchQuery searchQuery = new MessageSearchQuery();
        searchQuery.setIdDestinataire(destinataire.getId());
        searchQuery.setIdEmetteur(emetteur.getId());

        SearchResult<MessageDto> result = messageApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getElements().get(0).getContenu()).isEqualTo("1");

        searchQuery.setIdDestinataire(destinataire1.getId());
        result = messageApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getElements().get(0).getContenu()).isEqualTo("2");

        UtilisateurDto emetteur1 = new UtilisateurDto();
        emetteur1.setNom("Destinataire");
        emetteur1.setPrenom("Prenom");
        emetteur1.setImageUrl("https://google.com");
        emetteur1.setTheme(Theme.CLAIR.name());
        emetteur1 = utilisateurApplicationService.creer(emetteur1);

        MessageDto message2 = generateMessage(destinataire1, emetteur1);
        message2.setContenu("3");
        messageApplicationService.envoyer(message2);

        searchQuery.setIdDestinataire(destinataire1.getId());
        searchQuery.setIdEmetteur(emetteur1.getId());
        result = messageApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getElements().get(0).getContenu()).isEqualTo("3");

        LocalDateTime beforeCreation = LocalDateTime.now();

        MessageDto message3 = generateMessage(destinataire1, emetteur1);
        message3.setContenu("4");
        messageApplicationService.envoyer(message3);

        searchQuery.setIdDestinataire(destinataire1.getId());
        searchQuery.setIdEmetteur(emetteur1.getId());
        searchQuery.setAfter(beforeCreation);
        result = messageApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getElements().get(0).getContenu()).isEqualTo("4");

        MessageDto message4 = generateMessage(destinataire1, emetteur1);
        message4.setContenu("5");
        messageApplicationService.envoyer(message4);

        result = messageApplicationService.rechercher(searchQuery);
        assertThat(result.getTotalElements()).isEqualTo(2);

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageApplicationService.rechercher(null), MessageApplicationServiceImpl.ERROR_SEARCH_QUERY_OBLIGATOIRE);
    }

    @Test
    void envoyer() {
        MessageDto message = generateMessage();
        message = messageApplicationService.envoyer(message);

        MessageDto result = messageApplicationService.lire(message.getId());
        assertThat(result).isNotNull();
        assertThat(result.getContenu()).isEqualTo("Mon contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageApplicationService.envoyer(null), MessageApplicationServiceImpl.ERROR_MESSAGE_OBLIGATOIRE);
    }

    @Test
    void modifier() {
        MessageDto message = generateMessage();
        message = messageApplicationService.envoyer(message);
        message.setContenu("Nouveau contenu");
        message = messageApplicationService.modifier(message);

        MessageDto result = messageApplicationService.lire(message.getId());
        assertThat(result).isNotNull();
        assertThat(result.getContenu()).isEqualTo("Nouveau contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageApplicationService.modifier(null), MessageApplicationServiceImpl.ERROR_MESSAGE_OBLIGATOIRE);
    }

    @Test
    void supprimer() {
        MessageDto message = generateMessage();
        message = messageApplicationService.envoyer(message);

        messageApplicationService.supprimer(message.getId());

        MessageDto result = messageApplicationService.lire(message.getId());
        assertThat(result).isNull();

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageApplicationService.supprimer(null), MessageApplicationServiceImpl.ERROR_ID_OBLIGATOIRE);

    }

    private MessageDto generateMessage() {
        MessageDto message = new MessageDto();
        message.setContenu("Mon contenu");

        UtilisateurDto destinataire = new UtilisateurDto();
        destinataire.setNom("Destinataire");
        destinataire.setPrenom("Prenom");
        destinataire.setImageUrl("https://google.com");
        destinataire.setTheme(Theme.CLAIR.name());
        message.setDestinataire(utilisateurApplicationService.creer(destinataire));

        UtilisateurDto emetteur = new UtilisateurDto();
        emetteur.setNom("Émetteur");
        emetteur.setPrenom("Prenom");
        emetteur.setImageUrl("https://google.com");
        emetteur.setTheme(Theme.CLAIR.name());
        message.setEmetteur(utilisateurApplicationService.creer(emetteur));
        return message;
    }

    private MessageDto generateMessage(UtilisateurDto destinataire, UtilisateurDto emetteur) {
        MessageDto message = new MessageDto();
        message.setDestinataire(destinataire);
        message.setEmetteur(emetteur);
        return message;
    }
}
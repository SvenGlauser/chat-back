package ch.sven.chat.application.message.service;

import ch.sven.chat.application.exception.ExceptionTestUtils;
import ch.sven.chat.application.message.dto.MessageDto;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.application.utilisateur.generator.UtilisateurGenerator;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.security.roles.PermissionsConstantes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;

import static ch.sven.chat.application.configuration.SecurityConfigurationTest.USER_TEST;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MessageApplicationServiceImplTest {

    @Autowired
    MessageApplicationServiceImpl messageApplicationService;

    @Autowired
    UtilisateurGenerator utilisateurGenerator;

    @Test
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
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
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
    void rechercher() {
        UtilisateurDto destinataire = utilisateurGenerator.generate("Destinataire");
        UtilisateurDto destinataire1 = utilisateurGenerator.generate("Destinataire1");
        UtilisateurDto emetteur = utilisateurGenerator.generate("Émetteur");

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

        UtilisateurDto emetteur1 = utilisateurGenerator.generate("Émetteur 1");

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
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
    void envoyer() {
        MessageDto message = generateMessage();
        message = messageApplicationService.envoyer(message);

        MessageDto result = messageApplicationService.lire(message.getId());
        assertThat(result).isNotNull();
        assertThat(result.getContenu()).isEqualTo("Mon contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageApplicationService.envoyer(null), MessageApplicationServiceImpl.ERROR_MESSAGE_OBLIGATOIRE);
    }

    @Test
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
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
    @WithMockUser(username = USER_TEST, authorities = {PermissionsConstantes.ROLE_UTILISATEUR})
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

        UtilisateurDto destinataire = utilisateurGenerator.generate("Destinataire");
        message.setDestinataire(destinataire);

        UtilisateurDto emetteur = utilisateurGenerator.generate("Émetteur");
        message.setEmetteur(emetteur);
        return message;
    }

    private MessageDto generateMessage(UtilisateurDto destinataire, UtilisateurDto emetteur) {
        MessageDto message = new MessageDto();
        message.setDestinataire(destinataire);
        message.setEmetteur(emetteur);
        return message;
    }
}
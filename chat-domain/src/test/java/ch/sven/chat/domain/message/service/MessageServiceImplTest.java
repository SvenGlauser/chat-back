package ch.sven.chat.domain.message.service;

import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.exception.ExceptionTestUtils;
import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.message.repository.MessageRepository;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @InjectMocks
    MessageServiceImpl messageService;

    @Mock
    MessageRepository messageRepository;

    @Test
    void lire() {
        Message message = generateMessage();
        message.setId(1L);

        Mockito.when(messageRepository.lire(Mockito.anyLong())).thenReturn(message);
        Message result = messageService.lire(message.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContenu()).isEqualTo("Mon contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageService.lire(null), MessageServiceImpl.ERROR_ID_OBLIGATOIRE);
    }

    @Test
    void rechercher() {
        Message message = generateMessage();
        message.setId(1L);

        MessageSearchQuery messageSearchQuery = new MessageSearchQuery();
        messageSearchQuery.setIdDestinataire(1L);
        messageSearchQuery.setIdEmetteur(1L);

        Mockito.when(messageRepository.rechercher(Mockito.any(MessageSearchQuery.class))).thenReturn(SearchResult.of(List.of(message)));
        SearchResult<Message> result = messageService.rechercher(messageSearchQuery);

        Assertions.assertThat(result.getTotalElements()).isPositive();
        assertThat(result.getElements().get(0)).isEqualTo(message);

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageService.rechercher(null), MessageServiceImpl.ERROR_SEARCH_QUERY_OBLIGATOIRE);
    }

    @Test
    void envoyer() {
        Message message = generateMessage();

        Mockito.when(messageRepository.envoyer(Mockito.any(Message.class))).thenReturn(message);
        Message result = messageService.envoyer(message);

        assertThat(result).isNotNull();
        assertThat(result.getContenu()).isEqualTo("Mon contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageService.envoyer(null), MessageServiceImpl.ERROR_MESSAGE_OBLIGATOIRE);

        message.setId(1L);
        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageService.envoyer(message), MessageServiceImpl.ERROR_ID_DOIT_ETRE_NULL);
    }

    @Test
    void modifier() {
        Message message = generateMessage();
        message.setId(1L);

        Mockito.when(messageRepository.lire(Mockito.anyLong())).thenReturn(message);
        Mockito.when(messageRepository.modifier(Mockito.any(Message.class))).thenReturn(message);
        Message result = messageService.modifier(message);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContenu()).isEqualTo("Mon contenu");

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageService.modifier(null), MessageServiceImpl.ERROR_MESSAGE_OBLIGATOIRE);

        Mockito.when(messageRepository.lire(Mockito.anyLong())).thenReturn(null);
        ExceptionTestUtils.assertCoherenceThrownError(() -> messageService.modifier(message), MessageServiceImpl.ERROR_MESSAGE_NON_TROUVE);
    }

    @Test
    void supprimer() {
        messageService.supprimer(1L);

        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> messageService.supprimer(null), MessageServiceImpl.ERROR_ID_OBLIGATOIRE);
    }

    private static Message generateMessage() {
        Message message = new Message();
        message.setContenu("Mon contenu");

        Utilisateur destinataire = new Utilisateur();
        destinataire.setNom("Destinataire");
        message.setDestinataire(destinataire);

        Utilisateur emetteur = new Utilisateur();
        emetteur.setNom("??metteur");
        message.setEmetteur(emetteur);
        return message;
    }
}
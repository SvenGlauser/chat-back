package ch.sven.chat.application.message.dto;

import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MessageDtoTest {

    @Test
    void messageDto() {
        Message message = new Message();
        message.setId(1L);
        message.setContenu("Le contenu de test");

        Utilisateur destinataire = new Utilisateur();
        destinataire.setNom("Destinataire");
        message.setDestinataire(destinataire);

        Utilisateur emetteur = new Utilisateur();
        emetteur.setNom("Émetteur");
        message.setEmetteur(emetteur);

        MessageDto result = new MessageDto(message);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContenu()).isEqualTo("Le contenu de test");
        assertThat(result.getEmetteur().getNom()).isEqualTo("Émetteur");
        assertThat(result.getDestinataire().getNom()).isEqualTo("Destinataire");
    }

    @Test
    void toDomainEntity() {
        MessageDto message = new MessageDto();
        message.setId(1L);
        message.setContenu("Le contenu de test");

        UtilisateurDto destinataire = new UtilisateurDto();
        destinataire.setNom("Destinataire");
        message.setDestinataire(destinataire);

        UtilisateurDto emetteur = new UtilisateurDto();
        emetteur.setNom("Émetteur");
        message.setEmetteur(emetteur);

        Message result = message.toDomain();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContenu()).isEqualTo("Le contenu de test");
        assertThat(result.getEmetteur().getNom()).isEqualTo("Émetteur");
        assertThat(result.getDestinataire().getNom()).isEqualTo("Destinataire");
    }
}
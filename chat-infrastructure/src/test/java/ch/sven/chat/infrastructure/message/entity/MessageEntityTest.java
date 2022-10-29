package ch.sven.chat.infrastructure.message.entity;

import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageEntityTest {

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

        MessageEntity result = new MessageEntity(message);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContenu()).isEqualTo("Le contenu de test");
        assertThat(result.getEmetteur().getNom()).isEqualTo("Émetteur");
        assertThat(result.getDestinataire().getNom()).isEqualTo("Destinataire");
    }

    @Test
    void toDomainEntity() {
        MessageEntity message = new MessageEntity();
        message.setId(1L);
        message.setContenu("Le contenu de test");

        UtilisateurEntity destinataire = new UtilisateurEntity();
        destinataire.setNom("Destinataire");
        message.setDestinataire(destinataire);

        UtilisateurEntity emetteur = new UtilisateurEntity();
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
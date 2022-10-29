package ch.sven.chat.domain.message.model;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

    @Test
    void valider() {
        Message message = new Message();

        ExceptionTestUtils.assertCoherenceThrownErrorList(
                message::valider,
                Message.ERROR_CONTENU_OBLIGATOIRE,
                Message.ERROR_DESTINATAIRE_OBLIGATOIRE,
                Message.ERROR_EMETTEUR_OBLIGATOIRE
        );
    }

    @Test
    void modifyFields() {
        Message message = new Message();

        Utilisateur oldDestinataire = new Utilisateur();
        oldDestinataire.setNom("OldDestinataire");
        message.setDestinataire(oldDestinataire);

        Utilisateur oldEmetteur = new Utilisateur();
        oldEmetteur.setNom("OldÉmetteur");
        message.setEmetteur(oldEmetteur);

        Message newMessage = new Message();
        newMessage.setContenu("Le contenu de test");

        Utilisateur destinataire = new Utilisateur();
        destinataire.setNom("Destinataire");
        newMessage.setDestinataire(destinataire);

        Utilisateur emetteur = new Utilisateur();
        emetteur.setNom("Émetteur");
        newMessage.setEmetteur(emetteur);

        message.modifyFields(newMessage);

        assertThat(message.getContenu()).isEqualTo("Le contenu de test");
        assertThat(message.getDestinataire().getNom()).isEqualTo("OldDestinataire");
        assertThat(message.getEmetteur().getNom()).isEqualTo("OldÉmetteur");
    }
}
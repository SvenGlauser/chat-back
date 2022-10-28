package ch.sven.chat.domain.message.model;

import ch.sven.chat.domain.common.Model;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.validation.Validation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe qui représente un message
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends Model<Message> {
    public static final String ERROR_CONTENU_OBLIGATOIRE = "Le contenu du message est obligatoire";
    public static final String ERROR_EMETTEUR_OBLIGATOIRE = "L'émetteur est obligatoire";
    public static final String ERROR_DESTINATAIRE_OBLIGATOIRE = "Le destinataire est obligatoire";

    public static final String FIELD_CONTENU = "contenu";
    private static final String FIELD_EMETTEUR = "emetteur";
    private static final String FIELD_DESTINATAIRE = "destinataire";

    private String contenu;
    private Utilisateur emetteur;
    private Utilisateur destinataire;

    @Override
    public Validation valider(Validation validation) {
        return validation
                .notNull(contenu, FIELD_CONTENU, ERROR_CONTENU_OBLIGATOIRE)
                .notNull(emetteur, FIELD_EMETTEUR, ERROR_EMETTEUR_OBLIGATOIRE)
                .notNull(destinataire, FIELD_DESTINATAIRE, ERROR_DESTINATAIRE_OBLIGATOIRE);
    }

    /**
     * Modifier les champs du model
     *
     * @return Le nouveau model
     */
    @Override
    public Message modifyFields(Message message) {
        this.contenu = message.contenu;
        this.emetteur = message.emetteur;
        this.destinataire = message.destinataire;
        return this;
    }
}

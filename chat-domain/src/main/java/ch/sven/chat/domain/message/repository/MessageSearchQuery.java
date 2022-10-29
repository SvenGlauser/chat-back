package ch.sven.chat.domain.message.repository;

import ch.sven.chat.domain.common.SearchQuery;
import ch.sven.chat.domain.validation.Validation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Éléments de recherche pour les messages
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageSearchQuery extends SearchQuery {
    public static final String ERROR_ID_DESTINATAIRE_OBLIGATOIRE = "L'id du destinataire est obligatoire";
    public static final String ERROR_ID_EMETTEUR_OBLIGATOIRE = "L'id de l'émetteur est obligatoire";
    private static final String FIELD_ID_DESTINATAIRE = "idDestinataire";
    private static final String FIELD_ID_EMETTEUR = "idEmetteur";

    private Long idDestinataire;
    private Long idEmetteur;
    private LocalDateTime after;

    @Override
    public Validation valider(Validation validation) {
        return validation
                .notNull(idDestinataire, FIELD_ID_DESTINATAIRE, ERROR_ID_DESTINATAIRE_OBLIGATOIRE)
                .notNull(idEmetteur, FIELD_ID_EMETTEUR, ERROR_ID_EMETTEUR_OBLIGATOIRE);
    }
}

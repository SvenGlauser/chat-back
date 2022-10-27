package ch.sven.chat.domain.message;

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
    private static final String FIELD_ID_DESTINATAIRE = "idDestinataire";
    private static final String ERROR_ID_DESTINATAIRE_OBLIGATOIRE = "L'id du destinataire est obligatoire";

    private long idDestinataire;
    private LocalDateTime after;

    @Override
    public Validation valider(Validation validation) {
        return validation.notNull(idDestinataire, FIELD_ID_DESTINATAIRE, ERROR_ID_DESTINATAIRE_OBLIGATOIRE);
    }
}

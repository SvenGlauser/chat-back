package ch.sven.chat.domain.common;

import ch.sven.chat.domain.validation.ValidationMethods;
import lombok.Data;

/**
 * Éléments de recherche communs
 */
@Data
public abstract class SearchQuery implements ValidationMethods {
    private Integer taille;
    private Integer index;
}

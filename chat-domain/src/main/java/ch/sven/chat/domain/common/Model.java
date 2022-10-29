package ch.sven.chat.domain.common;

import ch.sven.chat.domain.validation.ValidationMethods;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe définissant les informations de base de chaque model
 * @param <E> Le model
 */
@Data
public abstract class Model<E> implements ValidationMethods {
    private Long id;
    private LocalDateTime creation;
    private LocalDateTime modification;

    /**
     * Permet de remplir l'instance avec les nouvelles valeurs
     * @param model Nouvelles valeurs
     * @return L'instance de l'éléments
     */
    public abstract E modifyFields(E model);
}

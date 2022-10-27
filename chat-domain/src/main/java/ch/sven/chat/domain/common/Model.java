package ch.sven.chat.domain.common;

import ch.sven.chat.domain.validation.ValidationMethods;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe définissant les informations de base de chaque model
 */
@Data
public abstract class Model<T> implements ValidationMethods {
    private Long id;
    private LocalDateTime creation;
    private LocalDateTime modification;

    /**
     * Permet de remplir l'instance avec les nouvelles valeurs
     * @param model Nouvelles valeurs
     * @return L'instance de l'éléments
     */
    public abstract T modifyFields(T model);
}

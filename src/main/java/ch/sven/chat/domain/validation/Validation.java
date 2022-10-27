package ch.sven.chat.domain.validation;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ErrorField;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe permettant de valider des models et des paramètres de classe
 */
public class Validation {
    private final List<ErrorField> errors = new ArrayList<>();
    private final Class<?> clazz;

    private Validation(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Instancier une nouvelle classe de validation
     * @param clazz La classe à valider
     * @return L'instance de validation
     */
    public static Validation of(Class<?> clazz) {
        return new Validation(clazz);
    }

    /**
     * Valider que la condition soit fausse
     * @param condition Condition à valider
     * @param field Champs à valider
     * @param message Message d'erreur si la condition est vrai
     * @return L'instance de validation
     */
    public Validation not(boolean condition, String field, String message) {
        if (condition) {
            errors.add(new ErrorField(clazz.getSimpleName(), field, message));
        }
        return this;
    }

    /**
     * Valider que l'objet n'est pas null
     * @param o Objet à valider
     * @param field Champs à valider
     * @param message Message d'erreur si la condition est null
     * @return L'instance de validation
     */
    public Validation notNull(Object o, String field, String message) {
        return not(Objects.isNull(o), field, message);
    }

    /**
     * Valider la classe
     */
    public void validate() {
        if (!CollectionUtils.isEmpty(errors)) {
            throw new CoherenceException(errors);
        }
    }
}

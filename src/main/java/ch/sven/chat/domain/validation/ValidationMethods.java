package ch.sven.chat.domain.validation;

/**
 * Méthode de validation
 */
public interface ValidationMethods {
    default void valider() {
        this.valider(Validation.of(this.getClass())).validate();
    }

    Validation valider(Validation validation);
}

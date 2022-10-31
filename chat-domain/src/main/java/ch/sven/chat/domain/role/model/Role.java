package ch.sven.chat.domain.role.model;

import ch.sven.chat.domain.common.Model;
import ch.sven.chat.domain.validation.Validation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe qui représente un rôle
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {
    public static final String ERROR_NOM_OBLIGATOIRE = "Le nom du rôle est obligatoire";
    private static final String FIELD_NOM = "nom";

    private String nom;

    @Override
    public Role modifyFields(Role role) {
        nom = role.getNom();
        return role;
    }

    @Override
    public Validation valider(Validation validation) {
        return validation.notNull(nom, FIELD_NOM, ERROR_NOM_OBLIGATOIRE);
    }
}

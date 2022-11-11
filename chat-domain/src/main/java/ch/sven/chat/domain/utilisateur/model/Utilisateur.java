package ch.sven.chat.domain.utilisateur.model;

import ch.sven.chat.domain.common.Model;
import ch.sven.chat.domain.validation.Validation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe qui représente un utilisateur
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Utilisateur extends Model<Utilisateur> {
    public static final String ERROR_NOM_OBLIGATOIRE = "Le nom est obligatoire";
    public static final String ERROR_PRENOM_OBLIGATOIRE = "Le prénom est obligatoire";
    public static final String ERROR_EMAIL_OBLIGATOIRE = "L'email est obligatoire";
    public static final String ERROR_IMAGE_URL_OBLIGATOIRE = "L'URL de l'image est obligatoire est obligatoire";
    public static final String ERROR_THEME_OBLIGATOIRE = "Le thème est obligatoire";

    private static final String FIELD_NOM = "nom";
    private static final String FIELD_PRENOM = "prenom";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_IMAGE_URL = "imageUrl";
    private static final String FIELD_THEME = "theme";

    private String keycloakId;
    private String nom;
    private String prenom;
    private String email;
    private String imageUrl;
    private Theme theme;

    @Override
    public Validation valider(Validation validation) {
        return validation
                .notNull(nom, FIELD_NOM, ERROR_NOM_OBLIGATOIRE)
                .notNull(prenom, FIELD_PRENOM, ERROR_PRENOM_OBLIGATOIRE)
                .notNull(prenom, FIELD_EMAIL, ERROR_EMAIL_OBLIGATOIRE)
                .notNull(imageUrl, FIELD_IMAGE_URL, ERROR_IMAGE_URL_OBLIGATOIRE)
                .notNull(theme, FIELD_THEME, ERROR_THEME_OBLIGATOIRE);
    }

    @Override
    public Utilisateur modifyFields(Utilisateur utilisateur) {
        this.imageUrl = utilisateur.imageUrl;
        this.theme = utilisateur.theme;
        return this;
    }
}

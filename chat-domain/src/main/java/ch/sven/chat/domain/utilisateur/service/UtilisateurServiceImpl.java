package ch.sven.chat.domain.utilisateur.service;

import ch.sven.chat.domain.common.Model;
import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepositoy;
import ch.sven.chat.domain.validation.Validation;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du utilisateur est obligatoire";
    public static final String ERROR_UTILISATEUR_OBLIGATOIRE = "Le utilisateur est obligatoire";
    public static final String ERROR_UTILISATEUR_NON_TROUVE = "Le utilisateur à modifier n'a pas été trouvé";
    public static final String ERROR_ID_DOIT_ETRE_NULL = "L'id de l'utilisateur doit être null";

    private static final String FIELD_ID = "id";
    private static final String FIELD_UTILISATEUR = "utilisateur";

    private final UtilisateurRepositoy utilisateurRepositoy;

    /**
     * Lire un utilisateur
     *
     * @param id Id de l'utilisateur
     * @return L'utilisateur
     */
    @Override
    public Utilisateur lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return utilisateurRepositoy.lire(id);
    }

    /**
     * Créer un nouvel utilisateur
     *
     * @param utilisateur Utilisateur à créer
     * @return Utilisateur créé
     */
    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        Validation.of(this.getClass())
                .notNull(utilisateur, FIELD_UTILISATEUR, ERROR_UTILISATEUR_OBLIGATOIRE)
                .isNull(Optional.ofNullable(utilisateur).map(Model::getId).orElse(null), FIELD_ID, ERROR_ID_DOIT_ETRE_NULL)
                .validate();

        utilisateur.valider();

        return utilisateurRepositoy.creer(utilisateur);
    }

    /**
     * Modifier un utilisateur
     *
     * @param utilisateur Utilisateur à modifier
     * @return Utilisateur modifié
     */
    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        Validation.of(this.getClass())
                .notNull(utilisateur, FIELD_UTILISATEUR, ERROR_UTILISATEUR_OBLIGATOIRE)
                .validate();

        Utilisateur oldUtilisateur = utilisateurRepositoy.lire(utilisateur.getId());
        if (Objects.isNull(oldUtilisateur)) {
            throw new CoherenceException(this.getClass().getSimpleName(), ERROR_UTILISATEUR_NON_TROUVE);
        }

        oldUtilisateur.modifyFields(utilisateur).valider();

        return utilisateurRepositoy.modifier(oldUtilisateur);
    }
}

package ch.sven.chat.domain.utilisateur.service;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.domain.validation.Validation;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du utilisateur est obligatoire";
    public static final String ERROR_UTILISATEUR_OBLIGATOIRE = "Le utilisateur est obligatoire";
    public static final String ERROR_UTILISATEUR_NON_TROUVE = "Le utilisateur à modifier n'a pas été trouvé";
    public static final String ERROR_ID_KEYCLOAK_OBLIGATOIRE = "L'id de Keycloak est obligatoire";

    private static final String FIELD_ID = "id";
    private static final String FIELD_UTILISATEUR = "utilisateur";
    private static final String FIELD_ID_KEYCLOAK = "idKeylcoak";

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return utilisateurRepository.lire(id);
    }

    @Override
    public Utilisateur lireIdKeycloak(String idKeycloak) {
        Validation.of(this.getClass())
                .notNull(idKeycloak, FIELD_ID_KEYCLOAK, ERROR_ID_KEYCLOAK_OBLIGATOIRE)
                .validate();

        return utilisateurRepository.lireIdKeycloak(idKeycloak);
    }

    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        Validation.of(this.getClass())
                .notNull(utilisateur, FIELD_UTILISATEUR, ERROR_UTILISATEUR_OBLIGATOIRE)
                .validate();

        Utilisateur oldUtilisateur = utilisateurRepository.lire(utilisateur.getId());
        if (Objects.isNull(oldUtilisateur)) {
            throw new CoherenceException(this.getClass().getSimpleName(), ERROR_UTILISATEUR_NON_TROUVE);
        }

        oldUtilisateur.modifyFields(utilisateur).valider();

        return utilisateurRepository.modifier(oldUtilisateur);
    }
}

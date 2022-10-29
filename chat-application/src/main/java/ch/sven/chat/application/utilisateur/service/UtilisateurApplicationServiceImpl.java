package ch.sven.chat.application.utilisateur.service;

import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.domain.utilisateur.service.UtilisateurService;
import ch.sven.chat.domain.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurApplicationServiceImpl implements UtilisateurApplicationService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du utilisateur est obligatoire";
    public static final String ERROR_UTILISATEUR_OBLIGATOIRE = "Le utilisateur est obligatoire";

    private static final String FIELD_ID = "id";
    private static final String FIELD_UTILISATEUR = "utilisateur";

    private final UtilisateurService utilisateurService;

    @Override
    public UtilisateurDto lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(utilisateurService.lire(id)).map(UtilisateurDto::new).orElse(null);
    }

    @Override
    public UtilisateurDto creer(UtilisateurDto utilisateur) {
        Validation.of(this.getClass())
                .notNull(utilisateur, FIELD_UTILISATEUR, ERROR_UTILISATEUR_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(utilisateurService.creer(utilisateur.toDomain())).map(UtilisateurDto::new).orElse(null);
    }

    @Override
    public UtilisateurDto modifier(UtilisateurDto utilisateur) {
        Validation.of(this.getClass())
                .notNull(utilisateur, FIELD_UTILISATEUR, ERROR_UTILISATEUR_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(utilisateurService.modifier(utilisateur.toDomain())).map(UtilisateurDto::new).orElse(null);
    }
}

package ch.sven.chat.application.utilisateur.service;

import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.domain.utilisateur.service.UtilisateurService;
import ch.sven.chat.domain.validation.Validation;
import ch.sven.chat.synchronisationutilisateur.utilisateur.UtilisateurSynchronisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UtilisateurApplicationServiceImpl implements UtilisateurApplicationService {
    public static final String ERROR_ID_OBLIGATOIRE = "L'id du utilisateur est obligatoire";
    public static final String ERROR_UTILISATEUR_OBLIGATOIRE = "Le utilisateur est obligatoire";
    public static final String ERROR_ID_KEYCLOAK_OBLIGATOIRE = "L'id de Keycloak est obligatoire";

    private static final String FIELD_ID = "id";
    private static final String FIELD_UTILISATEUR = "utilisateur";
    private static final String FIELD_ID_KEYCLOAK = "idKeylcoak";

    private final UtilisateurService utilisateurService;
    private final UtilisateurSynchronisationService utilisateurSynchronisationService;

    @Override
    @PostAuthorize("hasAuthority(@permissionsConstantes.ROLE_UTILISATEUR)")
    public UtilisateurDto lire(Long id) {
        Validation.of(this.getClass())
                .notNull(id, FIELD_ID, ERROR_ID_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(utilisateurService.lire(id)).map(UtilisateurDto::new).orElse(null);
    }

    @Override
    @Transactional
    @PostAuthorize("hasAuthority(@permissionsConstantes.ROLE_UTILISATEUR)")
    public UtilisateurDto modifier(UtilisateurDto utilisateur) {
        Validation.of(this.getClass())
                .notNull(utilisateur, FIELD_UTILISATEUR, ERROR_UTILISATEUR_OBLIGATOIRE)
                .validate();

        return Optional.ofNullable(utilisateurService.modifier(utilisateur.toDomain())).map(UtilisateurDto::new).orElse(null);
    }

    @Override
    @Transactional
    @PostAuthorize("hasAuthority(@permissionsConstantes.ROLE_UTILISATEUR)")
    public boolean synchroniser(String idKeycloak) {
        Validation.of(this.getClass())
                .notNull(idKeycloak, FIELD_ID_KEYCLOAK, ERROR_ID_KEYCLOAK_OBLIGATOIRE)
                .validate();

        return utilisateurSynchronisationService.synchroniser(idKeycloak);
    }
}

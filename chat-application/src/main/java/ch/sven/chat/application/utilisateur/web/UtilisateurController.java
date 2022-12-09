package ch.sven.chat.application.utilisateur.web;

import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.application.utilisateur.service.UtilisateurApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/utilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurApplicationService utilisateurApplicationService;

    /**
     * Récupérer un utilisateur
     * @param id Id de l'utilisateur à récupérer
     * @return L'utilisateur qui a été récupéré
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<UtilisateurDto> lire(@PathVariable("id") Long id) {
        UtilisateurDto response = utilisateurApplicationService.lire(id);
        return new ResponseEntity<>(response, Objects.nonNull(response) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Récupérer un utilisateur
     * @param idKeycloak Id Keycloak de l'utilisateur à récupérer
     * @return L'utilisateur qui a été récupéré
     */
    @GetMapping(path = "/keycloak/{idKeycloak}")
    public ResponseEntity<UtilisateurDto> lireKeycloak(@PathVariable("idKeycloak") String idKeycloak) {
        UtilisateurDto response = utilisateurApplicationService.lireIdKeycloak(idKeycloak);
        return new ResponseEntity<>(response, Objects.nonNull(response) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Synchroniser un utilisateur Keycloak avec la base de données local
     * @param idKeycloak Id Keycloak de l'utilisateur à synchroniser
     */
    @PostMapping(path = "/synchroniser/{idKeycloak}")
    public ResponseEntity<Void> synchroniser(@PathVariable("idKeycloak") String idKeycloak) {
        boolean result = utilisateurApplicationService.synchroniser(idKeycloak);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Modifier un utilisateur
     * @param utilisateur Utilisateur à modifier
     * @return Utilisateur modifié
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtilisateurDto> modifier(@RequestBody UtilisateurDto utilisateur) {
        return new ResponseEntity<>(utilisateurApplicationService.modifier(utilisateur), HttpStatus.OK);
    }
}

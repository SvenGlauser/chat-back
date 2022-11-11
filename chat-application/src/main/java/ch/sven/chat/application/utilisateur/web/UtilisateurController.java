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

    @GetMapping(path = "/{id}")
    public ResponseEntity<UtilisateurDto> lire(@PathVariable("id") Long id) {
        UtilisateurDto response = utilisateurApplicationService.lire(id);
        return new ResponseEntity<>(response, Objects.nonNull(response) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtilisateurDto> modifier(@RequestBody UtilisateurDto utilisateur) {
        return new ResponseEntity<>(utilisateurApplicationService.modifier(utilisateur), HttpStatus.OK);
    }
}

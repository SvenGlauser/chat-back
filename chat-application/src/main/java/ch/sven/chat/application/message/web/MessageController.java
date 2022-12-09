package ch.sven.chat.application.message.web;

import ch.sven.chat.application.message.dto.MessageDto;
import ch.sven.chat.application.message.service.MessageApplicationService;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.message.repository.MessageSearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageController {

    private final MessageApplicationService messageApplicationService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<MessageDto> lire(@PathVariable("id") Long id) {
        MessageDto response = messageApplicationService.lire(id);
        return new ResponseEntity<>(response, Objects.nonNull(response) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult<MessageDto>> rechercher(@RequestBody MessageSearchQuery searchQuery) {
        return new ResponseEntity<>(messageApplicationService.rechercher(searchQuery), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> envoyer(@RequestBody MessageDto message) {
        return new ResponseEntity<>(messageApplicationService.envoyer(message), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> modifier(@RequestBody MessageDto message) {
        return new ResponseEntity<>(messageApplicationService.modifier(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        messageApplicationService.supprimer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

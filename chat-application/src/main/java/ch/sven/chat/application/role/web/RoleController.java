package ch.sven.chat.application.role.web;

import ch.sven.chat.application.role.dto.RoleDto;
import ch.sven.chat.application.role.service.RoleApplicationService;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.role.repository.RoleSearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleApplicationService roleApplicationService;

    @GetMapping
    public ResponseEntity<SearchResult<RoleDto>> rechercher(RoleSearchQuery searchQuery) {
        return new ResponseEntity<>(roleApplicationService.rechercher(searchQuery), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoleDto> lire(@PathVariable("id") Long id) {
        RoleDto response = roleApplicationService.lire(id);
        return new ResponseEntity<>(response, Objects.nonNull(response) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> modifier(@RequestBody RoleDto role) {
        return new ResponseEntity<>(roleApplicationService.modifier(role), HttpStatus.OK);
    }
}

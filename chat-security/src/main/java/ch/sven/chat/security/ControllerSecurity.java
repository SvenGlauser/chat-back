package ch.sven.chat.security;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sec", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerSecurity {
    @GetMapping
    public Object getTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
}

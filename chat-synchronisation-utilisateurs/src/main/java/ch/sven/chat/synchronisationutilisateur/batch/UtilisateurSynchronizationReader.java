package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.synchronisationutilisateur.keycloak.KeycloakAdmin;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Objects;

@JobScope
@Component
@RequiredArgsConstructor
public class UtilisateurSynchronizationReader implements ItemReader<UserRepresentation> {

    private final KeycloakAdmin keycloakAdmin;

    private LinkedList<UserRepresentation> list;

    @Override
    public UserRepresentation read() {
        if (Objects.isNull(list)) {
            list = recupererUtilisateurs();
        }
        return list.isEmpty() ? null : list.pop();
    }

    private LinkedList<UserRepresentation> recupererUtilisateurs() {

        return new LinkedList<>(keycloakAdmin.getUtilisateurs());
    }
}

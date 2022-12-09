package ch.sven.chat.synchronisationutilisateur.keycloak;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakAdminImpl implements KeycloakAdmin {
    @Value("${chat.auth-server-url}")
    private String authServerUrl;

    @Value("${chat.realm}")
    private String realm;

    @Value("${chat.administration.user}")
    private String user;

    @Value("${chat.administration.password}")
    private String password;

    @Value("${chat.administration.realm}")
    private String adminRealm;

    @Value("${chat.administration.resource}")
    private String adminClient;

    private Keycloak keycloak;

    @Override
    public List<UserRepresentation> getUtilisateurs() {
        return getRealm().users().list();
    }

    @Override
    public UserRepresentation getUtilisateur(String id) {
        return getRealm().users().get(id).toRepresentation();
    }

    private RealmResource getRealm() {
        if (keycloak == null || keycloak.isClosed()) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(authServerUrl)
                    .grantType(OAuth2Constants.PASSWORD)
                    .realm(adminRealm)
                    .clientId(adminClient)
                    .username(user)
                    .password(password)
                    .resteasyClient(
                            new ResteasyClientBuilderImpl().connectionPoolSize(10).build()
                    ).build();
        }
        keycloak.tokenManager().getAccessToken();
        return keycloak.realm(realm);
    }
}

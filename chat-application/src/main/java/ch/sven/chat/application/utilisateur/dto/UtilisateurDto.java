package ch.sven.chat.application.utilisateur.dto;

import ch.sven.chat.application.common.Dto;
import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class UtilisateurDto extends Dto<Utilisateur> {
    private String keycloakId;
    private String nom;
    private String prenom;
    private String email;
    private String imageUrl;
    private String theme;

    /**
     * Constructeur paramétré
     * @param utilisateur Utilisateur
     */
    public UtilisateurDto(Utilisateur utilisateur) {
        super(utilisateur);
        keycloakId = utilisateur.getKeycloakId();
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        email = utilisateur.getEmail();
        imageUrl = utilisateur.getImageUrl();
        theme = Optional.ofNullable(utilisateur.getTheme()).map(Enum::name).orElse(null);
    }

    @Override
    protected Utilisateur toDomainEntity() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setKeycloakId(keycloakId);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setImageUrl(imageUrl);
        utilisateur.setTheme(Optional.ofNullable(theme).map(Theme::valueOf).orElse(null));
        return utilisateur;
    }
}

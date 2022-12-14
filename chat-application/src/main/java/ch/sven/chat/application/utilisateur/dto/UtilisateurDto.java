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
    private String nom;
    private String prenom;
    private String imageUrl;
    private String theme;

    /**
     * Constructeur paramétré
     * @param utilisateur Utilisateur
     */
    public UtilisateurDto(Utilisateur utilisateur) {
        super(utilisateur);
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        imageUrl = utilisateur.getImageUrl();
        theme = Optional.ofNullable(utilisateur.getTheme()).map(Enum::name).orElse(null);
    }

    @Override
    protected Utilisateur toDomainEntity() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setImageUrl(imageUrl);
        utilisateur.setTheme(Optional.ofNullable(theme).map(Theme::valueOf).orElse(null));
        return utilisateur;
    }
}

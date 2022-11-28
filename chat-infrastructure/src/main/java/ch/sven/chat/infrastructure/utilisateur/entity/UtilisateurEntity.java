package ch.sven.chat.infrastructure.utilisateur.entity;

import ch.sven.chat.domain.utilisateur.model.Theme;
import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.infrastructure.common.Dao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "UTILISATEUR")
@Table(name = "UTILISATEUR", schema = "CHAT")
@NoArgsConstructor
public class UtilisateurEntity extends Dao<Utilisateur> {
    @Column(name = "ID_KEYCLOAK", updatable = false)
    private String keycloakId;

    @Column(name = "NOM", nullable = false)
    private String nom;

    @Column(name = "PRENOM", nullable = false)
    private String prenom;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "THEME", nullable = false)
    private Theme theme;

    /**
     * Constructeur paramétré
     * @param utilisateur Utilisateur
     */
    public UtilisateurEntity(Utilisateur utilisateur) {
        super(utilisateur);
        keycloakId = utilisateur.getKeycloakId();
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        email = utilisateur.getEmail();
        imageUrl = utilisateur.getImageUrl();
        theme = utilisateur.getTheme();
    }

    @Override
    protected Utilisateur toDomainEntity() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setKeycloakId(keycloakId);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setImageUrl(imageUrl);
        utilisateur.setTheme(theme);
        return utilisateur;
    }
}

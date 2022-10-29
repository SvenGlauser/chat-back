package ch.sven.chat.infrastructure.message.entity;

import ch.sven.chat.domain.message.model.Message;
import ch.sven.chat.infrastructure.common.Dao;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@Entity(name = "MESSAGE")
@Table(name = "MESSAGE", schema = "CHAT")
@NoArgsConstructor
public class MessageEntity extends Dao<Message> {
    @Column(name = "CONTENU", nullable = false)
    private String contenu;

    @ManyToOne(targetEntity = UtilisateurEntity.class)
    @JoinColumn(name = "ID_EMETTEUR", nullable = false)
    private UtilisateurEntity emetteur;

    @ManyToOne(targetEntity = UtilisateurEntity.class)
    @JoinColumn(name = "ID_DESTINATAIRE", nullable = false)
    private UtilisateurEntity destinataire;

    public MessageEntity(Message message) {
        super(message);
        this.contenu = message.getContenu();
        this.emetteur = Optional.ofNullable(message.getEmetteur()).map(UtilisateurEntity::new).orElse(null);
        this.destinataire = Optional.ofNullable(message.getDestinataire()).map(UtilisateurEntity::new).orElse(null);
    }

    @Override
    protected Message toDomainEntity() {
        Message message = new Message();
        message.setContenu(contenu);
        message.setDestinataire(Optional.ofNullable(destinataire).map(Dao::toDomain).orElse(null));
        message.setEmetteur(Optional.ofNullable(emetteur).map(Dao::toDomain).orElse(null));
        return message;
    }
}

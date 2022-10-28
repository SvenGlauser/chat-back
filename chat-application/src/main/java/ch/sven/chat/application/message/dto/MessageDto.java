package ch.sven.chat.application.message.dto;

import ch.sven.chat.application.common.Dto;
import ch.sven.chat.application.utilisateur.dto.UtilisateurDto;
import ch.sven.chat.domain.message.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto extends Dto<Message> {

    private String contenu;
    private UtilisateurDto emetteur;
    private UtilisateurDto destinataire;

    public MessageDto(Message message) {
        super(message);
        this.contenu = message.getContenu();
        this.emetteur = Optional.ofNullable(message.getEmetteur()).map(UtilisateurDto::new).orElse(null);
        this.destinataire = Optional.ofNullable(message.getDestinataire()).map(UtilisateurDto::new).orElse(null);
    }

    @Override
    protected Message toDomainEntity() {
        Message message = new Message();
        message.setContenu(contenu);
        message.setDestinataire(Optional.ofNullable(destinataire).map(Dto::toDomain).orElse(null));
        message.setEmetteur(Optional.ofNullable(emetteur).map(Dto::toDomain).orElse(null));
        return message;
    }
}

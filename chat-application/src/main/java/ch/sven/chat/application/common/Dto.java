package ch.sven.chat.application.common;

import ch.sven.chat.domain.common.Model;
import ch.sven.chat.domain.message.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class Dto<E extends Model<E>> {
    private Long id;
    private LocalDateTime creation;
    private LocalDateTime modification;

    protected Dto(E model) {
        id = model.getId();
        creation = model.getCreation();
        modification = model.getModification();
    }

    /**
     * Transformer en object métier
     * @return Objet métier
     */
    public E toDomain() {
        E e = toDomainEntity();
        e.setId(id);
        e.setCreation(creation);
        e.setModification(modification);
        return e;
    }

    protected abstract E toDomainEntity();
}

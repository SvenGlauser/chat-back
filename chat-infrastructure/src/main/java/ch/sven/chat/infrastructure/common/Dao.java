package ch.sven.chat.infrastructure.common;

import ch.sven.chat.domain.common.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class Dao<E extends Model<E>> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @CreatedDate
    @Column(name = "CREATION", updatable = false, nullable = false)
    private LocalDateTime creation;

    @LastModifiedDate
    @Column(name = "MODIFICATION", updatable = false, nullable = false)
    private LocalDateTime modification;

    protected Dao(E model) {
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

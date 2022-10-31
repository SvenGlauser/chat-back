package ch.sven.chat.domain.role.repository;

import ch.sven.chat.domain.common.SearchQuery;
import ch.sven.chat.domain.validation.Validation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleSearchQuery extends SearchQuery {

    private String nom;

    @Override
    public Validation valider(Validation validation) {
        return validation;
    }
}

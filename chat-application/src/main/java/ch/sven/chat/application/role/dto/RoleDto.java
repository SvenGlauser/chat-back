package ch.sven.chat.application.role.dto;

import ch.sven.chat.application.common.Dto;
import ch.sven.chat.domain.role.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe qui représente un rôle
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends Dto<Role> {

    private String nom;

    public RoleDto(Role role) {
        super(role);
        nom = role.getNom();
    }

    @Override
    protected Role toDomainEntity() {
        Role role = new Role();
        role.setNom(nom);
        return role;
    }
}

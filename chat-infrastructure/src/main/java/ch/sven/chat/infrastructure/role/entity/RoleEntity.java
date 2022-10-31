package ch.sven.chat.infrastructure.role.entity;

import ch.sven.chat.domain.role.model.Role;
import ch.sven.chat.infrastructure.common.Dao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "ROLE")
@Table(name = "ROLE", schema = "CHAT")
@NoArgsConstructor
public class RoleEntity extends Dao<Role> {

    @Column(name = "NOM")
    private String nom;

    public RoleEntity(Role role) {
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

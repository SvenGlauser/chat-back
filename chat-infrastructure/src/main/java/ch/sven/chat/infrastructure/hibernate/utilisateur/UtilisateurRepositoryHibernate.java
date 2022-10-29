package ch.sven.chat.infrastructure.hibernate.utilisateur;

import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepositoryHibernate extends JpaRepository<UtilisateurEntity, Long> {
}

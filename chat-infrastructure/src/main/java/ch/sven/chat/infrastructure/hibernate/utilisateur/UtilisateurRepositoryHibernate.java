package ch.sven.chat.infrastructure.hibernate.utilisateur;

import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepositoryHibernate extends JpaRepository<UtilisateurEntity, Long> {
    Optional<UtilisateurEntity> findByKeycloakId(String keycloakId);
}

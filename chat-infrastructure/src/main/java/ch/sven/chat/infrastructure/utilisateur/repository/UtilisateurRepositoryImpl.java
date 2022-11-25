package ch.sven.chat.infrastructure.utilisateur.repository;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import ch.sven.chat.infrastructure.common.Dao;
import ch.sven.chat.infrastructure.hibernate.utilisateur.UtilisateurRepositoryHibernate;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UtilisateurRepositoryImpl implements UtilisateurRepository {
    private final UtilisateurRepositoryHibernate utilisateurRepositoryHibernate;

    @Override
    public Utilisateur lire(Long id) {
        return utilisateurRepositoryHibernate.findById(id).map(Dao::toDomain).orElse(null);
    }

    @Override
    public Utilisateur lireIdKeycloak(String idKeycloak) {
        return utilisateurRepositoryHibernate.findByKeycloakId(idKeycloak).map(Dao::toDomain).orElse(null);
    }

    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        return Optional.of(utilisateurRepositoryHibernate.save(new UtilisateurEntity(utilisateur))).map(Dao::toDomain).orElse(null);
    }

    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        return Optional.of(utilisateurRepositoryHibernate.save(new UtilisateurEntity(utilisateur))).map(Dao::toDomain).orElse(null);
    }
}

package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.infrastructure.hibernate.utilisateur.UtilisateurRepositoryHibernate;
import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UtilisateurSynchronisationWriter implements ItemWriter<UtilisateurEntity> {
    private final UtilisateurRepositoryHibernate utilisateurRepositoy;

    @Override
    public void write(List<? extends UtilisateurEntity> list) throws Exception {
        list.forEach(utilisateurRepositoy::save);
    }
}

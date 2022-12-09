package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.domain.utilisateur.model.Utilisateur;
import ch.sven.chat.domain.utilisateur.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UtilisateurSynchronisationWriter implements ItemWriter<Utilisateur> {
    private final UtilisateurRepository utilisateurRepositoy;

    @Override
    public void write(List<? extends Utilisateur> list) {
        list.forEach(utilisateur -> {
            if (Objects.nonNull(utilisateur.getId())) {
                utilisateurRepositoy.modifier(utilisateur);
            } else {
                utilisateurRepositoy.creer(utilisateur);
            }
        });
    }
}

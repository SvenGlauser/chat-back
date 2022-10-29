package ch.sven.chat.domain.message.repository;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import org.junit.jupiter.api.Test;

class MessageSearchQueryTest {

    @Test
    void valider() {
        MessageSearchQuery searchQuery = new MessageSearchQuery();
        searchQuery.setIdDestinataire(1L);
        searchQuery.setIdEmetteur(1L);
        searchQuery.valider();

        searchQuery.setIdDestinataire(null);

        ExceptionTestUtils.assertCoherenceThrownErrorList(searchQuery::valider, MessageSearchQuery.ERROR_ID_DESTINATAIRE_OBLIGATOIRE);
    }
}
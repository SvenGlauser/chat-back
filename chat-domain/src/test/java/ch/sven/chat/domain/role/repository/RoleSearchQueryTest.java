package ch.sven.chat.domain.role.repository;

import ch.sven.chat.domain.exception.CoherenceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class RoleSearchQueryTest {

    @Test
    void valider() {
        RoleSearchQuery searchQuery = new RoleSearchQuery();

        try {
            searchQuery.valider();
        } catch (CoherenceException ignored) {
            fail("Aucune CoherenceException attendue");
        }
    }
}
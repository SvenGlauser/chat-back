package ch.sven.chat.infrastructure.utils.search;

import ch.sven.chat.domain.common.SearchQuery;
import ch.sven.chat.domain.common.SearchResult;
import ch.sven.chat.domain.validation.Validation;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SearchUtilsTest {

    @Test
    void transform() {
        Object object = new Object();
        Object object1 = new Object();
        Object object2 = new Object();

        List<Object> elements = List.of(
                object,
                object1,
                object2
        );

        Page<Object> page = new PageImpl<>(
                elements,
                PageRequest.of(0, 10),
                3
        );

        SearchResult<Object> searchResult = SearchUtils.of(page);

        SearchResult<Boolean> result = SearchUtils.transform(searchResult, o -> o.equals(object1));
        assertThat(result).isNotNull();
        assertThat(result.getElements()).isEqualTo(List.of(
                false,
                true,
                false
        ));
        assertThat(result.getIndex()).isZero();
        assertThat(result.getNombreElementsPage()).isEqualTo(10);
        assertThat(result.getNombrePages()).isEqualTo(1);
        assertThat(result.getTotalElements()).isEqualTo(3);

    }

    @Test
    void of() {
        Object object = new Object();
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        Object object4 = new Object();
        Object object5 = new Object();
        Object object6 = new Object();
        Object object7 = new Object();
        Object object8 = new Object();
        Object object9 = new Object();

        List<Object> elements = List.of(
                object,
                object1,
                object2,
                object3,
                object4,
                object5,
                object6,
                object7,
                object8,
                object9
        );

        Page<Object> page = new PageImpl<>(
                elements,
                PageRequest.of(0, 10),
                10
        );

        SearchResult<Object> searchResult = SearchUtils.of(page);
        assertThat(searchResult).isNotNull();
        assertThat(searchResult.getElements()).isEqualTo(elements);
        assertThat(searchResult.getIndex()).isZero();
        assertThat(searchResult.getNombreElementsPage()).isEqualTo(10);
        assertThat(searchResult.getNombrePages()).isEqualTo(1);
        assertThat(searchResult.getTotalElements()).isEqualTo(10);
    }

    @Test
    void getPageRequest() {
        SearchQuery searchQuery = new SearchQuery() {
            @Override
            public Validation valider(Validation validation) {
                return validation;
            }
        };

        PageRequest pageRequest = SearchUtils.getPageRequest(searchQuery);
        assertThat(pageRequest).isNotNull();
        assertThat(pageRequest.getPageNumber()).isZero();
        assertThat(pageRequest.getPageSize()).isEqualTo(10);

        searchQuery.setIndex(2);
        searchQuery.setTaille(20);

        pageRequest = SearchUtils.getPageRequest(searchQuery);
        assertThat(pageRequest).isNotNull();
        assertThat(pageRequest.getPageNumber()).isEqualTo(2);
        assertThat(pageRequest.getPageSize()).isEqualTo(20);
    }
}
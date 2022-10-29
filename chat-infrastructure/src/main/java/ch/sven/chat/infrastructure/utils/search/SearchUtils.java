package ch.sven.chat.infrastructure.utils.search;

import ch.sven.chat.domain.common.SearchQuery;
import ch.sven.chat.domain.common.SearchResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchUtils {

    /**
     * Transformer {@link SearchResult <T>} en {@link SearchResult<R>}
     * @param searchResult {@link SearchResult} actuel
     * @param transformation Function de transformation pour changer le type
     * @return Un nouveau {@link SearchResult}
     * @param <T> Ancien type
     * @param <R> Nouveau type
     */
    public static <T, R> SearchResult<R> transform(SearchResult<T> searchResult, Function<T, R> transformation) {
        SearchResult<R> result = new SearchResult<>();
        result.setIndex(searchResult.getIndex());
        result.setNombrePages(searchResult.getNombrePages());
        result.setNombreElementsPage(searchResult.getNombreElementsPage());
        result.setTotalElements(searchResult.getTotalElements());
        result.setElements(searchResult.getElements().stream().map(transformation).collect(Collectors.toList()));
        return result;
    }

    /**
     * Créer un nouveau {@link SearchResult<T>}
     * @param page La page
     * @return Un nouveau {@link SearchResult}
     * @param <T> Le type d'éléments
     */
    public static <T> SearchResult<T> of(Page<T> page) {
        SearchResult<T> result = new SearchResult<>();
        result.setIndex(page.getPageable().getPageNumber());
        result.setNombrePages(page.getTotalPages());
        result.setNombreElementsPage(page.getPageable().getPageSize());
        result.setTotalElements(page.getTotalElements());
        result.setElements(page.getContent());
        return result;
    }

    /**
     * Récupérer une {@link PageRequest}
     * @param searchQuery Éléments de recherche
     * @return Une {@link PageRequest}
     */
    public static PageRequest getPageRequest(SearchQuery searchQuery) {
        return PageRequest.of(
                Objects.nonNull(searchQuery.getIndex()) ? searchQuery.getIndex() : 0,
                Objects.nonNull(searchQuery.getTaille()) ? searchQuery.getTaille() : 10
        );
    }
}

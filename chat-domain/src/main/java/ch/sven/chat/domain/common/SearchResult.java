package ch.sven.chat.domain.common;

import lombok.Data;

import java.util.List;

/**
 * Éléments de recherche communs
 */
@Data
public class SearchResult<T> {
    private int nombrePages;
    private int nombreElementsPage;
    private long totalElements;
    private int index;
    private List<T> elements;

    /**
     * Créer un SearchResult à partir d'une liste d'éléments
     * @param elements La liste d'éléments
     * @return Un SearchResult
     * @param <T> Le type d'éléments
     */
    public static <T> SearchResult<T> of(List<T> elements) {
        SearchResult<T> searchResult = new SearchResult<>();
        searchResult.setIndex(1);
        searchResult.setElements(elements);
        searchResult.setNombrePages(1);
        searchResult.setNombreElementsPage(elements.size());
        searchResult.setTotalElements(elements.size());
        return searchResult;
    }
}

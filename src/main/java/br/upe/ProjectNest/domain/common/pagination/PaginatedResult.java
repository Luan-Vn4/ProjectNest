package br.upe.ProjectNest.domain.common.pagination;

import java.util.Collection;

/**
 * Utilizada para representar resultados de buscas que utilizam paginação para retornar os elementos do
 * banco de dados aos poucos, conforme o tamanho das páginas informadas pelo cliente
 * @param <T> tipo do conteúdo que estará contido na paginação
 */
public interface PaginatedResult<T> {

    /**
     * @return elementos presentes na página atual
     */
    Collection<T> getContent();

    /**
     * @return página atual a partir de 1
     */
    int getCurrentPage();

    /**
     * @return total de páginas
     */
    int getTotalPages();

    /**
     * @return quantidade total de elementos que a página deve ter
     */
    int getMaxPageSize();

    /**
     * @return quantidade de elementos da página atual
     */
    int getSize();

    /**
     * @return quantidade total de elementos encontrados na busca
     */
    int getTotalElements();

}

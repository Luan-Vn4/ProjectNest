package br.upe.ProjectNest.domain.common.pagination;

import java.util.Collection;

public interface PaginatedResult<T> {

    Collection<T> getContent();

    int getCurrentPage();

    int getTotalPages();

    int getMaxPageSize();

    int getSize();

    int getTotalElements();

}

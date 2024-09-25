package br.upe.ProjectNest.domain.common.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import java.util.Collection;

public class SpringPageAdapter<T> implements PaginatedResult<T> {

    private final Page<T> page;

    private SpringPageAdapter(Page<T> page) {
        this.page = page;
    }

    public static <T> SpringPageAdapter<T> from(Page<T> page) {
        return new SpringPageAdapter<>(page);
    }

    @Override
    @JsonProperty("content")
    public Collection<T> getContent() {
        return page.getContent();
    }

    @Override
    @JsonProperty("currentPage")
    public int getCurrentPage() {
        return page.getNumber();
    }

    @Override
    @JsonProperty("totalPages")
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    @JsonProperty("maxPageSize")
    public int getMaxPageSize() {
        return page.getSize();
    }

    @Override
    @JsonProperty("pageSize")
    public int getSize() {
        return page.getSize();
    }

    @Override
    @JsonProperty("totalElements")
    public int getTotalElements() {
        return (int) page.getTotalElements();
    }

}

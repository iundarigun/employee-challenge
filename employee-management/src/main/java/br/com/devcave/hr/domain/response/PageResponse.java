package br.com.devcave.hr.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponse<T> {
    private final Integer currentPage;

    private final Integer totalPages;

    private final Long totalElements;

    private final List<T> content;
}

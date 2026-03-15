package com.curso.autoticketapp.common.domain.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationQuery {

    private int page;
    private int size;
    private String sortBy;
    private String direction;

}

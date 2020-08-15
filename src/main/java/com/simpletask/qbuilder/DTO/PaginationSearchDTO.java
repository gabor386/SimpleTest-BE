package com.simpletask.qbuilder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class PaginationSearchDTO {
    private int size;
    private int page;
    private String searchText;
}

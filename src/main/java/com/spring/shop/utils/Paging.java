package com.spring.shop.utils;

import org.springframework.stereotype.Component;

@Component
public class Paging {

    public SearchDto generate(SearchDto dto) {
        int limit = ((dto.getCurPage() - 1) * dto.getPagePerCnt()) + dto.getPagePerCnt();
        int offset = (dto.getCurPage() - 1) * dto.getPagePerCnt();

        System.out.println(limit);
        System.out.println(offset);

        dto.setLimit(limit);
        dto.setOffset(offset);

        return dto;
    }
}

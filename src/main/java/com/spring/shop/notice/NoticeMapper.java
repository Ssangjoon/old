package com.spring.shop.notice;

import java.util.List;

public interface NoticeMapper {

    List<NoticeDto> list();

    int add(NoticeDto noticeDto);

    NoticeDto select(NoticeDto dto);

    int edit(NoticeDto noticeDto);

    int delete(NoticeDto noticeDto);
}

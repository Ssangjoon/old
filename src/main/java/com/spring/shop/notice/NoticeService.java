package com.spring.shop.notice;

import java.util.List;

public interface NoticeService {

    List<NoticeDto> list();

    int add(NoticeDto noticeDto);

    NoticeDto select(NoticeDto noticeDto);

    int edit(NoticeDto noticeDto);

    int delete(NoticeDto noticeDto);
}

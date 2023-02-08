package com.spring.shop.notice;

import java.util.List;

public interface NoticeService {
    Notice create(Notice notice);
    List<Notice> read();
    void delete(Notice notice);
    Notice findById(Notice notice);
}

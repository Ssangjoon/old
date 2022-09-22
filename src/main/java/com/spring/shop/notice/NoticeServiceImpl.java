package com.spring.shop.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<NoticeDto> list() {
        return noticeRepository.list();
    }

    @Override
    public int add(NoticeDto noticeDto) {
        return noticeRepository.add(noticeDto);
    }

    @Override
    public NoticeDto select(NoticeDto dto) {
        return noticeRepository.select(dto);
    }

    @Override
    public int edit(NoticeDto noticeDto) {
        return noticeRepository.edit(noticeDto);
    }

    @Override
    public int delete(NoticeDto noticeDto) {
        return noticeRepository.delete(noticeDto);
    }
}

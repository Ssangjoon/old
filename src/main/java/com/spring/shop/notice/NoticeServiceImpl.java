package com.spring.shop.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Notice create(Notice notice) {
        return noticeRepository.save(notice);
    }
    @Override
    public List<Notice> read() {
        return noticeRepository.findAll();
    }
    @Override
    public Notice findById(Notice notice) {
        Optional<Notice> selectNotice = noticeRepository.findById(notice.getId());
        Notice select = selectNotice.get();
//        select.getMember().getId()
        return select;
    }

    @Override
    public void delete(Notice notice) {
        System.out.println("삭제");
        noticeRepository.deleteById(notice.getId());
    }


}


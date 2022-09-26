package com.spring.shop.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    @Override
    public Notice create(Notice notice) {
        Optional<Notice> optionalNotice = noticeRepository.findById(notice.getId());
        if(optionalNotice.isEmpty()) {
            return noticeRepository.save(notice);
        }
        return noticeRepository.save(optionalNotice.get());
    }

    @Override
    public List<Notice> read() {
        return noticeRepository.findAll();
    }

//    @Override
//    public Notice update(Notice notice) {
//        System.out.println("업데이트 실행");
//        Optional<Notice> oldNotice = noticeRepository.findById(notice.getId());
//        Notice newNotice = oldNotice.get();
//        System.out.println(newNotice);
//        return noticeRepository.save(newNotice);
//    }

}

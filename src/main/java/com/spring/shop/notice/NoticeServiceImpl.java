package com.spring.shop.notice;

import lombok.RequiredArgsConstructor;
import org.junit.After;
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
        return select;
    }

    @Override
    public void delete(Notice notice) {
        System.out.println("삭제");
        noticeRepository.deleteById(notice.getId());
    }


    //Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드
    //보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다.
    //여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할 수 있다.
    @After
    public void cleanup() {
        noticeRepository.deleteAll();
    }





}


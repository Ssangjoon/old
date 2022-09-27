package com.spring.shop.notice;

import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateData;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateRequest;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateResponse;
import com.spring.shop.notice.dto.NoticeDeleteDto.NoticeDeleteRequest;
import com.spring.shop.notice.dto.NoticeSelectDto.NoticeSelectData;
import com.spring.shop.notice.dto.NoticeSelectDto.NoticeSelectRequest;
import com.spring.shop.notice.dto.NoticeSelectDto.NoticeSelectResponse;
import com.spring.shop.notice.dto.NoticeUpdateDto.NoticeUpdateData;
import com.spring.shop.notice.dto.NoticeUpdateDto.NoticeUpdateRequest;
import com.spring.shop.notice.dto.NoticeUpdateDto.NoticeUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.
@RequestMapping("/notice")
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/create")
    @ResponseBody
    public NoticeCreateResponse create(@RequestBody NoticeCreateRequest req) {
        Notice notice = noticeService.create(req.toEntity());
        // 여기서 저장이 된다?
        return new NoticeCreateResponse(NoticeCreateData.create(notice));
        // 그렇다면 여기서 응답 데이터를 만드는 이유가 뭘까?
    }

    @GetMapping(value = "/read")
    @ResponseBody
    public List<Notice> read(){
        log.info("공지 리스트 조회");
        List<Notice> noticeList = noticeService.read();
        System.out.println(noticeList);
        return noticeList;
    }

    @PostMapping(value = "/select")
    @ResponseBody
    public NoticeSelectResponse select(@RequestBody NoticeSelectRequest req){
        Notice notice = noticeService.findById(req.toEntity());
        System.out.println(notice.getMember());
        return new NoticeSelectResponse(NoticeSelectData.select(notice));
    }

    @PostMapping("/update")
    @ResponseBody
    public NoticeUpdateResponse update(@RequestBody NoticeUpdateRequest req) {
        Notice notice = noticeService.findById(req.toEntity());
//        Notice notice = req.toEntity(); // 받은 값을 dto로 만든다.
//        Long authorId = noticeService.findById(notice).getMember().getId();
//        System.out.println("id="+authorId);
//        if(authorId == notice.getMember().getId()){
//            System.out.println("저장함");
////            notice = noticeService.create(notice);
//            return new NoticeUpdateResponse(NoticeUpdateData.update(noticeService.create(notice)));
//        }
//        System.out.println("저장안함");
        return new NoticeUpdateResponse(NoticeUpdateData.update(notice));
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody NoticeDeleteRequest req) {
        noticeService.delete(req.toEntity());
    }

}

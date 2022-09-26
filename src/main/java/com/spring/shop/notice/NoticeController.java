package com.spring.shop.notice;

import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateData;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateRequest;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateResponse;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeUpdateRequest;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeUpdateResponse;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeUpdateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.
@RequestMapping("/notice")
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
        List<Notice> noticeList = noticeService.read();
        return noticeList;
    }

    @PostMapping("/update")
    @ResponseBody
    public NoticeUpdateResponse update(@RequestBody NoticeUpdateRequest req) {
        Notice notice = noticeService.create(req.toEntity());

        return new NoticeUpdateResponse(NoticeUpdateData.update(notice));
    }

}

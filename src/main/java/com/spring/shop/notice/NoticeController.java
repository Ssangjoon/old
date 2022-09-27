package com.spring.shop.notice;

import com.spring.shop.notice.dto.notice.NoticeCreateDto.NoticeCreateData;
import com.spring.shop.notice.dto.notice.NoticeCreateDto.NoticeCreateRequest;
import com.spring.shop.notice.dto.notice.NoticeCreateDto.NoticeCreateResponse;
import com.spring.shop.notice.dto.notice.NoticeDeleteDto.NoticeDeleteRequest;
import com.spring.shop.notice.dto.notice.NoticeSelectDto.NoticeSelectData;
import com.spring.shop.notice.dto.notice.NoticeSelectDto.NoticeSelectRequest;
import com.spring.shop.notice.dto.notice.NoticeSelectDto.NoticeSelectResponse;
import com.spring.shop.notice.dto.notice.NoticeUpdateDto.NoticeUpdateData;
import com.spring.shop.notice.dto.notice.NoticeUpdateDto.NoticeUpdateRequest;
import com.spring.shop.notice.dto.notice.NoticeUpdateDto.NoticeUpdateResponse;
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

    @PostMapping(value = "/select")
    @ResponseBody
    public NoticeSelectResponse select(@RequestBody NoticeSelectRequest req){
        Notice notice = noticeService.findById(req.toEntity());
        return new NoticeSelectResponse(NoticeSelectData.select(notice));
    }

    @PostMapping("/update")
    @ResponseBody
    public NoticeUpdateResponse update(@RequestBody NoticeUpdateRequest req) {
        Notice notice = noticeService.create(req.toEntity());

        return new NoticeUpdateResponse(NoticeUpdateData.update(notice));
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody NoticeDeleteRequest req) {
        noticeService.delete(req.toEntity());
    }

}

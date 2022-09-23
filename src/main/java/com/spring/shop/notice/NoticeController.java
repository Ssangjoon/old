package com.spring.shop.notice;

import com.spring.shop.notice.dto.NoticeCreateDto;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateData;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateRequest;
import com.spring.shop.notice.dto.NoticeCreateDto.NoticeCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/create")
    @ResponseBody
    public NoticeCreateResponse create(@RequestBody NoticeCreateRequest req) {
        Notice notice = noticeService.create(req.toEntity());

        return new NoticeCreateResponse(NoticeCreateData.create(notice));

    }
}

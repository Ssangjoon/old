package com.spring.shop.login;

import com.spring.shop.login.dto.member.MemberCreateDto.MemberCreateRequest;
import com.spring.shop.login.dto.member.MemberCreateDto.MemberCreateResponse;
import com.spring.shop.login.dto.member.MemberCreateDto.MemberCreateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.
@RequestMapping("/member")
public class MemberController {
    
    private final MemberService memberService;

    @PostMapping("/create")
    @ResponseBody
    public MemberCreateResponse create(@RequestBody MemberCreateRequest req) {
        Member Member = memberService.create(req.toEntity());
        // 여기서 저장이 된다?
        return new MemberCreateResponse(MemberCreateData.create(Member));
        // 그렇다면 여기서 응답 데이터를 만드는 이유가 뭘까?
    }
}

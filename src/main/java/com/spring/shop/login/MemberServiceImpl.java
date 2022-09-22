package com.spring.shop.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public int joinMember(MemberDto dto) {
        return memberRepository.joinMember(dto);
    }

    @Override
    public MemberDto joinIn(MemberDto dto) {
        return memberRepository.joinIn(dto);
    }

    @Override
    public int userEdit(MemberDto dto) {
        return memberRepository.userEdit(dto);
    }
}

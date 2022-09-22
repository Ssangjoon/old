package com.spring.shop.login;

public interface MemberRepository {

    int joinMember(MemberDto dto);

    int userEdit(MemberDto dto);

    MemberDto joinIn(MemberDto dto);
}

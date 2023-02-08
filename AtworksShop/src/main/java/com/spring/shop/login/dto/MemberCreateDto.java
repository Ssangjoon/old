package com.spring.shop.login.dto;

import com.spring.shop.login.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

public class MemberCreateDto {

    @Getter
    @AllArgsConstructor // 모든 필드 값을 받는 생성자를 만든다.
    @NoArgsConstructor // 기본 생성자를 생성한다.
    public static class MemberCreateRequest {
        private String name;

        public Member toEntity() {
            return new Member(this.name);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberCreateResponse {
        private MemberCreateData data;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberCreateData {
        private long id;
        private String name;
        private OffsetDateTime createdAt;

        public static MemberCreateData create(Member member) {
            return new MemberCreateData(member.getId(), member.getName(), member.getCreatedAt());
        }
    }


}

package com.spring.shop.notice.dto;

import com.spring.shop.login.Member;
import com.spring.shop.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

public class NoticeCreateDto {

    @Getter
    @AllArgsConstructor // 모든 필드 값을 받는 생성자를 만든다.
    @NoArgsConstructor // 기본 생성자를 생성한다.
    public static class NoticeCreateRequest {
        private String title;
        private String content;
        private String writer;
        private Member member;
        public Notice toEntity() {
            return new Notice(this.title, this.content, this.writer, this.member);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeCreateResponse {
        private NoticeCreateData data;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeCreateData {
        private long id;
        private String title;
        private String content;
        private String writer;
        private OffsetDateTime createdAt;

        public static NoticeCreateData create(Notice notice) {
            return new NoticeCreateData(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getCreatedAt());
        }
    }


}

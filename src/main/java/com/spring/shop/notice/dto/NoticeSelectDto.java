package com.spring.shop.notice.dto;

import com.spring.shop.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

public class NoticeSelectDto {

    @Getter
    @AllArgsConstructor // 모든 필드 값을 받는 생성자를 만든다.
    @NoArgsConstructor // 기본 생성자를 생성한다.
    public static class NoticeSelectRequest {
        private Long id;
        private String title;
        private String content;
        private String writer;

        public Notice toEntity() {
            return new Notice(this.id, this.title, this.content, this.writer);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeSelectResponse {
        private NoticeSelectDto.NoticeSelectData data;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeSelectData {
        private long id;
        private String title;
        private String content;
        private String writer;
        private OffsetDateTime createdAt;

        public static NoticeSelectData select(Notice notice) {
            return new NoticeSelectData(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getCreatedAt());
        }
    }
}

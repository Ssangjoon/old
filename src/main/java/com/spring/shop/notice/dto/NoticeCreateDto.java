package com.spring.shop.notice.dto;

import com.spring.shop.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

public class NoticeCreateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeCreateRequest {
        private String title;
        private String content;
        private String writer;

        public Notice toEntity() {
            return new Notice(this.title, this.content, this.writer);
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

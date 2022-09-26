package com.spring.shop.notice.dto;

import com.spring.shop.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

public class NoticeUpdateDto {

    @Getter
    @AllArgsConstructor // 모든 필드 값을 받는 생성자를 만든다.
    @NoArgsConstructor // 기본 생성자를 생성한다.
    public static class NoticeUpdateRequest {
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
    public static class NoticeUpdateResponse {
        private NoticeUpdateData data;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeUpdateData {
        private long id;
        private String title;
        private String content;
        private String writer;
        private OffsetDateTime createdAt;

        public static NoticeUpdateData update(Notice notice) {
            return new NoticeUpdateData(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getCreatedAt());
        }
    }
}

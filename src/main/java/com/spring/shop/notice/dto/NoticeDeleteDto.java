package com.spring.shop.notice.dto;

import com.spring.shop.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoticeDeleteDto {

    @Getter
    @AllArgsConstructor // 모든 필드 값을 받는 생성자를 만든다.
    @NoArgsConstructor // 기본 생성자를 생성한다.
    public static class NoticeDeleteRequest {
        private Long id;
        private String title;
        private String content;
        private String writer;

        public Notice toEntity() {
            return new Notice(this.id, this.title, this.content, this.writer);
        }
    }

//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class NoticeDeleteResponse {
//        private NoticeDeleteDto.NoticeDeleteData data;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class NoticeDeleteData {
//        private long id;
//        private String title;
//        private String content;
//        private String writer;
//        private OffsetDateTime createdAt;
//
//        public static NoticeDeleteData delete(Notice notice) {
//            return new NoticeDeleteData(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getCreatedAt());
//        }
//    }
}

package com.spring.shop.notice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity // JPA에서 해당 객체를 관리하겠다는 어노테이션
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id // 해당 필드가 식별자 역할을 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자 생성전략?
    private Long id;

    @Column(length = 50, nullable = false) // 해당 필드가 db에 적용되는 속성들
    private String title;

    @Column(length = 255, nullable = false)
    private String content;

    @Column(length = 30, nullable = false)
    private String writer;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Notice(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Notice(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void updateNotice(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}

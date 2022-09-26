package com.spring.shop.notice;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity // JPA에서 해당 객체를 관리하겠다는 어노테이션
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id // 해당 테이블의 pk 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 규칙이다.
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
    @Builder // 해당클래스의 빌터 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public void updateNotice(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}

package com.spring.shop.notice;

import com.spring.shop.login.Member;
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

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 매핑,엔티티 조회할 때, 연관된 엔티티는 실제 사용 시점에 조회한다.
    @JoinColumn(name = "member_id") //
    private Member member;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Notice(String title, String content, String writer, Member member) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.member = member;
    }

    public Notice(Long id, String title, String content, String writer, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.member = member;
    }
}

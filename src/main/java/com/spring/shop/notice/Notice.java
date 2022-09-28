package com.spring.shop.notice;

import com.spring.shop.login.Member;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity // JPA에서 해당 객체를 관리하겠다는 어노테이션
@Getter
@ToString(exclude = "member")
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

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

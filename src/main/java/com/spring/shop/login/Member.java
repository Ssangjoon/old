package com.spring.shop.login;

import com.spring.shop.notice.Notice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA에서 해당 객체를 관리하겠다는 어노테이션
@Getter
@ToString(exclude = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id // 해당 테이블의 pk 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk    생성 규칙이다.
    private Long id;
    @Column(length = 50, nullable = false) // 해당 필드가 db에 적용되는 속성들
    private String name;

    @OneToMany(mappedBy = "member") // 양방향 매핑에서 참조 당하는 엔티티에서 사용한다.
    private List<Notice> notice = new ArrayList<>();

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Member(Long id, String name, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Member(String name) {
        this.name = name;
    }
}




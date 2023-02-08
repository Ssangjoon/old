package com.spring.shop.notice;

import org.springframework.data.jpa.repository.JpaRepository;

// DB Layer 접근자.
// JPA에서는 Repository라고 브르며 인터페이스로 생성한다.
// JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메서드가 자동으로 생성된다.
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

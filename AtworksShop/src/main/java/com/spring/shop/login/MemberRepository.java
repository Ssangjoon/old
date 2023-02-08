package com.spring.shop.login;

import com.spring.shop.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

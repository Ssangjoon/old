package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.Entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class MemberRepositoryImpl  implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}

package com.spring.shop.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int joinMember(MemberDto dto) {
        return sqlSession.getMapper(MemberMapper.class).joinMember(dto);
    }

    @Override
    public int userEdit(MemberDto dto) {
        return sqlSession.getMapper(MemberMapper.class).userEdit(dto);
    }

    @Override
    public MemberDto joinIn(MemberDto dto) {
        return sqlSession.getMapper(MemberMapper.class).joinIn(dto);
    }
}

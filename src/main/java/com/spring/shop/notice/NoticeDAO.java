package com.spring.shop.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<NoticeDto> list() {
        return sqlSession.getMapper(NoticeMapper.class).list();

    }

    @Override
    public int add(NoticeDto noticeDto) {
        return sqlSession.getMapper(NoticeMapper.class).add(noticeDto);
    }

    @Override
    public NoticeDto select(NoticeDto dto) {
        return sqlSession.getMapper(NoticeMapper.class).select(dto);
    }

    @Override
    public int edit(NoticeDto noticeDto) {
        return sqlSession.getMapper(NoticeMapper.class).edit(noticeDto);
    }

    @Override
    public int delete(NoticeDto noticeDto) {
        return sqlSession.getMapper(NoticeMapper.class).delete(noticeDto);
    }
}
package com.simple_board.main.dao;

import javax.inject.Inject;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple_board.main.vo.NoticeVO;

@Repository
public class MainDAOImpl implements MainDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(MainDAOImpl.class);
	
	// Notice 리스트 조회
	@Override
	public List<NoticeVO> selectNoticeList() throws Exception {
		logger.info("mainMapper.selectNoticeList");
		return sqlSession.selectList("mainMapper.selectNoticeList");
	}
}

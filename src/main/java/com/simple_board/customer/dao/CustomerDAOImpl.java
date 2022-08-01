package com.simple_board.customer.dao;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple_board.adm.vo.FaqVO;
import com.simple_board.customer.vo.NoticeVO;
import com.simple_board.utils.PagingVO;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);
	
	// Notice 수
	@Override
	public int countNoticeList(PagingVO vo) {
		return sqlSession.selectOne("customerMapper.countNoticeList", vo);
	}
	// Notice 리스트 조회
	@Override
	public List<NoticeVO> selectNoticeList(PagingVO vo) throws Exception {
		logger.info("admMapper.selectNoticeList");
		return sqlSession.selectList("customerMapper.selectNoticeList", vo);
	}
	// Notice 조회
	@Override
	public NoticeVO selectNotice(NoticeVO vo) throws Exception {
		logger.info("admMapper.selectNotice");
		return sqlSession.selectOne("customerMapper.selectNotice", vo);
	}
	// Notice 파일 조회
	@Override
	public List<Map<String, Object>> selectNoticeFile(int notice_seq) throws Exception {
		return sqlSession.selectList("customerMapper.selectFileList", notice_seq);
	}
	// Notice 조회수 증가
	@Override
	public void updateViewCount(NoticeVO vo) throws Exception {
		sqlSession.update("customerMapper.updateViewCount", vo);
	}
	// FAQ 수
	@Override
	public int countFaqList(PagingVO vo) {
		return sqlSession.selectOne("customerMapper.countFaqList", vo);
	}
	// FAQ 리스트 조회
	@Override
	public List<FaqVO> selectFaqList(PagingVO vo) throws Exception {
		return sqlSession.selectList("customerMapper.selectFaqList", vo);
	}
}

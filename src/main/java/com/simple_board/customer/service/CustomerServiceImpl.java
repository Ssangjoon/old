package com.simple_board.customer.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.simple_board.adm.vo.FaqVO;
import com.simple_board.customer.dao.CustomerDAO;
import com.simple_board.customer.vo.NoticeVO;
import com.simple_board.utils.PagingVO;

import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerDAO dao;
	
	// Notice 수
	@Override
	public int countNoticeList(PagingVO vo) throws Exception {
		return dao.countNoticeList(vo);
	}
	// Notice 리스트 조회
	@Override
	public List<NoticeVO> selectNoticeList(PagingVO vo) throws Exception {
		return dao.selectNoticeList(vo);
	}
	// Notice 조회
	@Override
	public NoticeVO selectNotice(NoticeVO vo) throws Exception {
		dao.updateViewCount(vo);
		return dao.selectNotice(vo);
	}
	// Notice 파일 조회
	@Override
	public List<Map<String, Object>> selectNoticeFile(int notice_seq) throws Exception {
		return dao.selectNoticeFile(notice_seq);
	}
	// FAQ 수
	@Override
	public int countFaqList(PagingVO vo) throws Exception {
		return dao.countFaqList(vo);
	}
	// FAQ 리스트 조회
	@Override
	public List<FaqVO> selectFaqList(PagingVO vo) throws Exception {
		return dao.selectFaqList(vo);
	}
}


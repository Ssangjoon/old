package com.simple_board.customer.service;

import java.util.List;
import java.util.Map;

import com.simple_board.adm.vo.FaqVO;
import com.simple_board.customer.vo.NoticeVO;
import com.simple_board.utils.PagingVO;

public interface CustomerService {

	// Notice 수
	public int countNoticeList(PagingVO vo) throws Exception;
	// Notice 리스트 조회
	public List<NoticeVO> selectNoticeList(PagingVO vo) throws Exception;
	// Notice 조회
	public NoticeVO selectNotice(NoticeVO vo) throws Exception;
	// Notice 파일 조회
	public List<Map<String, Object>> selectNoticeFile(int notice_seq) throws Exception;
	// FAQ 수
	public int countFaqList(PagingVO vo) throws Exception;
	// FAQ 리스트 조회
	public List<FaqVO> selectFaqList(PagingVO vo) throws Exception;
}

package com.simple_board.main.service;

import java.util.List;

import com.simple_board.main.vo.NoticeVO;

public interface MainService {

	// Notice 리스트 조회
	public List<NoticeVO> selectNoticeList() throws Exception;
	
}

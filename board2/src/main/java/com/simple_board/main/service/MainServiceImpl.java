package com.simple_board.main.service;

import java.util.List;

import javax.inject.Inject;

import com.simple_board.main.dao.MainDAO;
import com.simple_board.main.vo.NoticeVO;

import org.springframework.stereotype.Service;

@Service("mainService")
public class MainServiceImpl implements MainService {

	@Inject
	private MainDAO dao;
	
	// Notice 리스트 조회
	@Override
	public List<NoticeVO> selectNoticeList() throws Exception {
		return dao.selectNoticeList();
	}
}


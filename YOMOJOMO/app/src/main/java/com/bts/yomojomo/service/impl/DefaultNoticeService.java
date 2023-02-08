package com.bts.yomojomo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bts.yomojomo.dao.NoticeDao;
import com.bts.yomojomo.domain.Notice;
import com.bts.yomojomo.service.NoticeService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class DefaultNoticeService implements NoticeService {

	@Autowired
	NoticeDao noticeDao;

	@Override
	public int add(Notice notice) {
		return noticeDao.insert(notice);
	}

	@Override
	public List<Notice> list() {
		return noticeDao.findAll();
	}

	@Override
	public Notice get(int no) {
		return noticeDao.findByNo(no);
	}

	@Override
	public int update(Notice notice) {
		return noticeDao.update(notice);
	}

	@Override
	public int delete(int no) {
		return noticeDao.delete(no);
	}

	@Override
	public Notice select(int no) {
		return noticeDao.findByNno(no);
	}

	@Override
	public List<Notice> listselect(int no, int cutno, String searchKeyword) {
		return noticeDao.getBoardListSelect(no, cutno, searchKeyword);
	}

	@Override
	public int countSelect(String searchKeyword) {
		return noticeDao.getBoardListSelectCount(searchKeyword);
	}

	
}

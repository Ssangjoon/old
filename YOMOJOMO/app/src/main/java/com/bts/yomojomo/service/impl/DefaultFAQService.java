package com.bts.yomojomo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bts.yomojomo.dao.FAQDao;
import com.bts.yomojomo.domain.FAQ;
import com.bts.yomojomo.service.FAQService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class DefaultFAQService implements FAQService {

	@Autowired
	FAQDao faqDao;

	@Override
	public int add(FAQ faq) {
		return faqDao.insert(faq);
	}

	@Override
	public List<FAQ> list() {
		return faqDao.findAll();
	}

	@Override
	public FAQ get(int no) {
		return faqDao.findByNo(no);
	}

	@Override
	public int update(FAQ faq) {
		return faqDao.update(faq);
	}

	@Override
	public int delete(int no) {
		return faqDao.delete(no);
	}

	@Override
	public FAQ select(int no) {
		return faqDao.findByNno(no);
	}

	@Override
	public List<FAQ> listselect(int no, int cutno, String searchKeyword) {
		return faqDao.getBoardListSelect(no, cutno, searchKeyword);
	}

	@Override
	public int countSelect(String searchKeyword) {
		return faqDao.getBoardListSelectCount(searchKeyword);
	}

	
}
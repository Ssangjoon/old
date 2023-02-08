package com.bts.yomojomo.service;

import java.util.List;

import com.bts.yomojomo.domain.FAQ;

public interface FAQService {

	int add(FAQ faq);

	List<FAQ> list();

	FAQ get(int no);

	int update(FAQ faq);

	int delete(int no);

	public FAQ select(int no);

	List<FAQ> listselect(int no, int cutno, String searchKeyword);

	int countSelect(String searchKeyword);
}

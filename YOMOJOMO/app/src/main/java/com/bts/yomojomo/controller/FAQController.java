package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bts.yomojomo.domain.FAQ;
import com.bts.yomojomo.service.FAQService;

@RestController
public class FAQController {
	@Autowired
	FAQService faqService;

	@RequestMapping("/faq/add")
	public Object add(FAQ faq) {
		faqService.add(faq);
		return new ResultMap().setStatus(SUCCESS);
	}

	@RequestMapping("/faq/get")
	public Object get(int no) {
		return faqService.get(no);
	}

	@RequestMapping("/faq/list")
	public Object list() {
		return faqService.list();
	}

	@RequestMapping("/faq/update")
	public Object update(FAQ faq) {
		faqService.update(faq);
		return new ResultMap().setStatus(SUCCESS);
	}

	@RequestMapping("/faq/delete")
	public Object delete(int no) {
		faqService.delete(no);
		return new ResultMap().setStatus(SUCCESS);
	}

	@RequestMapping("/faq/select")
	public Object select(int no) {
		return faqService.select(no);
	}

	@RequestMapping("/faq/listselect")
	public Object listselect(int no, int cutno, String searchKeyword) {
		int mNo = (no - 1) * cutno; // 넘어온 값에 -1로 데이터베이스에 맞춘다
		return faqService.listselect(mNo, cutno, searchKeyword);
	}

	@RequestMapping("/faq/faqcountselect")
	public int getBoardListSelectCount(String searchKeyword) {
		return faqService.countSelect(searchKeyword);
	}
}
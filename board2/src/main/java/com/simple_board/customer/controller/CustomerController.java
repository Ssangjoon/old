package com.simple_board.customer.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple_board.customer.service.CustomerService;
import com.simple_board.customer.vo.NoticeVO;
import com.simple_board.utils.PagingVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	
	
	/* 상담안내 */
	@RequestMapping(value = "customer/consult.do", method = RequestMethod.GET)
	public String consult(Model model) {
		logger.info("CustomerController consult");
		model.addAttribute("gNum", "04" );
		model.addAttribute("sNum", "01" );
		model.addAttribute("gName", "사업분야" );
		model.addAttribute("sName", "상담안내" );
		return "customer/consult.tiles";
	}
	
	/* 공지사항 */
	@RequestMapping(value = "customer/notice.do", method = RequestMethod.GET)
	public String notice(PagingVO vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
		String keyword = vo.getSearch_key();
		String option = vo.getSearch_option();
		int total = customerService.countNoticeList(vo);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
		System.out.println("astart :" +  vo.getStart());
		System.out.println("aend :" +  vo.getCntPerPage());
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		System.out.println("bstart :" +  vo.getStart());
		System.out.println("bend :" +  vo.getCntPerPage());
		vo.setSearch_key(keyword);
		vo.setSearch_option(option);
		model.addAttribute("keyword", keyword);
		model.addAttribute("option", option);
		model.addAttribute("paging", vo);
		model.addAttribute("list", customerService.selectNoticeList(vo));
		
		logger.info("CustomerController notice");
		model.addAttribute("gNum", "04" );
		model.addAttribute("sNum", "02" );
		model.addAttribute("gName", "사업분야" );
		model.addAttribute("sName", "공지사항" );
		

		
		return "customer/notice.tiles";
	}
	
	/* 공지사항 상세 */
	@RequestMapping(value = "customer/noticeDetail.do", method = RequestMethod.GET)
	public String noticeDetail(NoticeVO vo, Model model) throws Exception {
		NoticeVO noticeOne = customerService.selectNotice(vo);
		model.addAttribute("list", noticeOne);
		
		List<Map<String, Object>> fileList = customerService.selectNoticeFile(noticeOne.getSeq());
		model.addAttribute("file", fileList);

		logger.info("CustomerController notice");
		model.addAttribute("gNum", "04" );
		model.addAttribute("sNum", "02" );
		model.addAttribute("gName", "사업분야" );
		model.addAttribute("sName", "공지사항" );
		return "customer/noticeDetail.tiles";
	}
	
	/* FAQ */
	@RequestMapping(value = "customer/faq.do", method = RequestMethod.GET)
	public String faq(PagingVO vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
		String keyword = vo.getSearch_key();
		String option = vo.getSearch_option();
		String pageType = vo.getPageType();
		int total = customerService.countFaqList(vo);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setSearch_key(keyword);
		vo.setSearch_option(option);
		vo.setPageType(pageType);
		model.addAttribute("pageType", pageType);
		model.addAttribute("keyword", keyword);
		model.addAttribute("option", option);
		model.addAttribute("paging", vo);
		model.addAttribute("list", customerService.selectFaqList(vo));
		
		logger.info("CustomerController faq");
		model.addAttribute("gNum", "04" );
		model.addAttribute("sNum", "03" );
		model.addAttribute("gName", "사업분야" );
		model.addAttribute("sName", "FAQ" );
		return "customer/faq.tiles";
	}
	
}

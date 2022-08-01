package com.simple_board.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	/* 이용약관 */
	@RequestMapping(value = "common/terms.do", method = RequestMethod.GET)
	public String terms(Model model) {
		logger.info("CommonController terms");
		model.addAttribute("gNum", "09" );
		model.addAttribute("sNum", "01" );
		model.addAttribute("gName", "이용약관" );
		model.addAttribute("sName", "이용약관" );
		return "common/terms.tiles";
	}
	
	/* 개인정보처리방침 */
	@RequestMapping(value = "common/privacy.do", method = RequestMethod.GET)
	public String privacy(Model model) {
		logger.info("CommonController privacy");
		model.addAttribute("gNum", "09" );
		model.addAttribute("sNum", "02" );
		model.addAttribute("gName", "개인정보처리방침" );
		model.addAttribute("sName", "개인정보처리방침" );
		return "common/privacy.tiles";
	}
}

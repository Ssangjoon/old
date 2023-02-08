package com.simple_board.adm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.simple_board.adm.service.AdmService;
import com.simple_board.utils.PagingVO;

//@Controller
public class AdmController2 {

  private static final Logger logger = LoggerFactory.getLogger(AdmController2.class); // 로그

  @Resource(name = "admService") // resource
  private AdmService admService;


  @Autowired
  private JavaMailSender mailSender;



  /* member */
  @RequestMapping(value = "adm/member.do", method = RequestMethod.GET)
  public String member(PagingVO vo, Model model 
      , @RequestParam(value="nowPage", required=false)String nowPage // @RequestParam 사용할때 규칙이 맞지 않는 쿼리문을 날려도 에러뜨지 않게 false 설정 함
      , @RequestParam(value="cntPerPage", required=false)String cntPerPage, HttpServletRequest request) throws Exception {
    logger.info("AdmController member");
    /* Session Check - START */
    //    HttpSession session = request.getSession();
    //    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    //    if(sessionUser == null) {
    //      return "redirect:/adm/login.do?rt=3";
    //
    //    }
    //    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "01" ); 
    String keyword = vo.getSearch_key();
    String option = vo.getSearch_option();
    int total = admService.countMemberList(vo);
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
    model.addAttribute("keyword", keyword);
    model.addAttribute("option", option);
    model.addAttribute("paging", vo);
    model.addAttribute("list", admService.selectMemberList(vo));

    return "adm/member.adm";
  }

  /* memWrite */
  @RequestMapping(value = "adm/memWrite.do", method = RequestMethod.GET)
  public String memWrite(Model model, HttpServletRequest request) {
    //    /* Session Check - START */
    //    HttpSession session = request.getSession();
    //    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    //    if(sessionUser == null) {
    //      return "redirect:/adm/login.do?rt=3";
    //    }
    //    model.addAttribute("user", sessionUser);
    //    /* Session Check - END */
    model.addAttribute("gNum", "01" );
    logger.info("AdmController memWrite");

    return "adm/memWrite.adm";
  }

}

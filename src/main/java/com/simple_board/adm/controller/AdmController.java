package com.simple_board.adm.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.simple_board.adm.service.AdmService;
import com.simple_board.adm.vo.FaqVO;
import com.simple_board.adm.vo.IdeaVO;
import com.simple_board.adm.vo.MemberVO;
import com.simple_board.adm.vo.NoticeVO;
import com.simple_board.utils.CommonUtils;
import com.simple_board.utils.PagingVO;

@Controller
public class AdmController {

  private static final Logger logger = LoggerFactory.getLogger(AdmController.class);

  @Resource(name = "admService")
  private AdmService admService;


  @Autowired
  private JavaMailSender mailSender;

  /* login */
  @RequestMapping(value = "adm/login.do", method = RequestMethod.GET)
  public String login(Model model, HttpServletRequest request) {
    logger.info("AdmController login");
    String message = request.getParameter("rt");
    model.addAttribute("rt", message);

    return "adm/login.login";
  }

  /* logout */
  @RequestMapping(value = "adm/logout.do", method = RequestMethod.GET)
  public String logout(Model model, HttpServletRequest request) {
    logger.info("AdmController logout");
    HttpSession session = request.getSession();
    session.invalidate();

    return "redirect:/adm/login.do";
  }

  /* loginFind */
  @RequestMapping(value = "adm/loginFind.do", method = RequestMethod.GET)
  public String loginFind(Model model, HttpServletRequest request) {
    String message = request.getParameter("rt");
    model.addAttribute("rt", message);

    return "adm/loginFind.login";
  }


  @RequestMapping(value = "/adm/mailSender.do", method = RequestMethod.POST)
  public String sendMailTest(MemberVO vo, Model model, HttpServletRequest request) throws Exception{
    // 메일 주소로 사용자 정보 가져오기
    MemberVO emailInfo = admService.selectEmailInfo(vo.getEmail());
    // 사용자 정보가 있을시 임시비밀번호 생성
    String content = "";
    try {
      if(emailInfo.getUid() != "") {
        System.out.println("id : " + emailInfo.getUid() );
        // 아이디가 있을경우 실패 확인
        if(emailInfo.getState() == 0) {
          // 잠김 확인
          System.out.println("getState : " + emailInfo.getState() );
          content  = "아이디는 [ " + emailInfo.getUid() + " ] 이며 계정이 비활성화 되어 있습니다.<br>관리담당자에게 문의해주세요.";
        }else{
          String password = CommonUtils.setPassword(8);
          emailInfo.setUpw(CommonUtils.sha256(password));
          admService.updatePassword(emailInfo);
          content = "아이디는 [ <Strong>" + emailInfo.getUid() + "</Strong> ] 이며 임시 비밀번호는 [ <Strong>" + password + "</Strong> ] 입니다.<br>"
              + "안전한 사용을 위하여 로그인 후 비밀번호를 변경해주세요.";
          // 임시 비밀번호 업데이트
        }
      }else {
        // 실패 메시지
        model.addAttribute("rt", "1");
        return "redirect:/adm/loginFind.do";
      }			
    } catch (Exception e) {
      model.addAttribute("rt", "1");
      return "redirect:/adm/loginFind.do";
    }
    String subject = "관리자 계정 찾기";
    String from = "deepest@kakao.com";
    String to = emailInfo.getEmail();

    try {
      MimeMessage mail = mailSender.createMimeMessage();
      MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
      mailHelper.setFrom(from);
      mailHelper.setFrom("<" + from + ">");
      mailHelper.setTo(to);
      mailHelper.setSubject(subject);
      mailHelper.setText(content, true);

      mailSender.send(mail);

    } catch(Exception e) {
      e.printStackTrace();
      // 실패 메시지
      model.addAttribute("rt", "2");
      return "redirect:/adm/loginFind.do";
    }
    model.addAttribute("rt", "4");
    return "redirect:/adm/login.do";
  }


  /* loginCheck */
  @RequestMapping(value = "adm/loginCheck.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  public String loginCheck(MemberVO vo, Model model, HttpServletRequest request) throws Exception {
    logger.info("AdmController login");
    vo.setUpw(CommonUtils.sha256(vo.getUpw()));
    // 로그인 조회
    // 로그인 실패카운트 조회,
    MemberVO loginInfo = admService.selectLogin(vo);
    String returnUrl = "redirect:/adm/login.do";
    try {
      if(loginInfo.getUid() != "") {
        System.out.println("id : " + loginInfo.getUid() );
        // 아이디가 있을경우 실패 확인
        if(loginInfo.getCheck_no() == 1) {
          // 잠김 확인
          System.out.println("getState : " + loginInfo.getState() );
          if(loginInfo.getState() == 0) {
            System.out.println("F");
            model.addAttribute("rt", "2");
          }else {
            // 로그인 성공
            System.out.println("S");
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginInfo);
            // 실패 카운트 초기화, 마지막 로그인 시간 업데이트
            admService.updateLogin(vo);
            // 세션 처리
            returnUrl = "redirect:/adm/member.do";
          }
        }else {
          if(loginInfo.getFail_upw() < 5) {
            // 실패카운트 증가
            vo.setFail_upw(loginInfo.getFail_upw() + 1);
            vo.setState(1);
            admService.updateFailLogin(vo);
            model.addAttribute("rt", "1");
          }else {
            // 로그인 잠금
            vo.setState(0);
            admService.updateFailLogin(vo);
            model.addAttribute("rt", "2");
          }
        }
      }else {
        // 실패 메시지
        model.addAttribute("rt", "1");
      }
    } catch (Exception e) {
      System.out.println(e);
      model.addAttribute("rt", "1");
    }

    return returnUrl;
  }

  /* member */
  @RequestMapping(value = "adm/member.do", method = RequestMethod.GET)
  public String member(PagingVO vo, Model model
      , @RequestParam(value="nowPage", required=false)String nowPage
      , @RequestParam(value="cntPerPage", required=false)String cntPerPage, HttpServletRequest request) throws Exception {
    logger.info("AdmController member");
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";

    }
    model.addAttribute("user", sessionUser);
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
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "01" );
    logger.info("AdmController memWrite");

    return "adm/memWrite.adm";
  }

  /* memInsert */
  @RequestMapping(value = "adm/memInsert.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String memInsert(MemberVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */
    logger.info("write");
    vo.setUpw(CommonUtils.sha256(vo.getUpw()));
    admService.insertMemberList(vo);

    return "{result:'성공'}";
  }

  /* memModify */
  @RequestMapping(value = "adm/memModify.do", method = RequestMethod.GET)
  public String memModify(MemberVO vo, Model model, HttpServletRequest request) throws Exception {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "01" );

    model.addAttribute("list", admService.selectMember(vo));
    return "adm/memModify.adm";
  }

  /* memUpdate */
  @RequestMapping(value = "adm/memUpdate.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String updateMember(MemberVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */		
    vo.setUpw(CommonUtils.sha256(vo.getUpw()));
    admService.updateMember(vo);

    return "{result:'성공'}";
  }

  /* memDelete */
  @RequestMapping(value = "adm/memDelete.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String memDelete(MemberVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */

    admService.deleteMember(vo);

    return "{result:'성공'}";
  }


  /* notice */
  @RequestMapping(value = "adm/notice.do", method = RequestMethod.GET)
  public String notice(PagingVO vo, Model model
      , @RequestParam(value="nowPage", required=false)String nowPage
      , @RequestParam(value="cntPerPage", required=false)String cntPerPage, HttpServletRequest request) throws Exception {
    logger.info("AdmController notice");
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "02" );
    String keyword = vo.getSearch_key();
    String option = vo.getSearch_option();
    int total = admService.countNoticeList(vo);
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
    model.addAttribute("list", admService.selectNoticeList(vo));

    return "adm/notice.adm";
  }


  /* noticeWrite */
  @RequestMapping(value = "adm/noticeWrite.do", method = RequestMethod.GET)
  public String noticeWrite(Model model, HttpServletRequest request) {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "02" );
    String message = request.getParameter("rt");
    model.addAttribute("rt", message);
    return "adm/noticeWrite.adm";
  }

  /* noticeInsert */
  @RequestMapping(value = "adm/noticeInsert.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  public String noticeInsert(NoticeVO vo, HttpServletRequest request, MultipartHttpServletRequest mpRequest) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */
    vo.setWriter(sessionUser.getName());
    admService.insertNoticeList(vo, mpRequest);

    return "redirect:/adm/noticeWrite.do?rt=1";
  }

  /* noticeModify */
  @RequestMapping(value = "adm/noticeModify.do", method = RequestMethod.GET)
  public String noticeModify(NoticeVO vo, Model model, HttpServletRequest request) throws Exception {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "02" );
    NoticeVO noticeOne = admService.selectNotice(vo);
    model.addAttribute("list", noticeOne);

    List<Map<String, Object>> fileList = admService.selectFileList(noticeOne.getSeq());
    model.addAttribute("file", fileList);
    String message = request.getParameter("rt");
    model.addAttribute("rt", message);
    return "adm/noticeModify.adm";
  }

  /* noticeUpdate */
  @RequestMapping(value = "adm/noticeUpdate.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  public String updateNotice(NoticeVO vo, HttpServletRequest request,  MultipartHttpServletRequest mpRequest) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */

    admService.updateNotice(vo, mpRequest);

    return "redirect:/adm/noticeModify.do?seq="+ vo.getSeq() + "&rt=1";
  }

  /* noticeDelete */
  @RequestMapping(value = "adm/noticeDelete.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String noticeDelete(NoticeVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */
    admService.deleteNotice(vo);
    return "{result:'성공'}";
  }

  /* fileDelete */
  @RequestMapping(value = "adm/deleteFile.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String deleteFile(NoticeVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */
    int seq = Integer.parseInt(request.getParameter("seq"));
    admService.deleteFile(seq);

    return "{result:'성공'}";
  }

  @RequestMapping(value="/adm/fileDown.do")
  public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception{
    int seq = Integer.parseInt(request.getParameter("file_seq"));
    Map<String, Object> resultMap = admService.selectFileInfo(seq);
    String storedFileName = (String) resultMap.get("stored_file");
    String originalFileName = (String) resultMap.get("org_file");

    // 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
    byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\upload\\"+storedFileName));

    response.setContentType("application/octet-stream");
    response.setContentLength(fileByte.length);
    response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
    response.getOutputStream().write(fileByte);
    response.getOutputStream().flush();
    response.getOutputStream().close();

  }

  /* faq */
  @RequestMapping(value = "adm/faq.do", method = RequestMethod.GET)
  public String faq(PagingVO vo, Model model
      , @RequestParam(value="nowPage", required=false)String nowPage
      , @RequestParam(value="cntPerPage", required=false)String cntPerPage, HttpServletRequest request) throws Exception {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "03" );
    String keyword = vo.getSearch_key();
    String option = vo.getSearch_option();
    int total = admService.countFaqList(vo);
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
    model.addAttribute("list", admService.selectFaqList(vo));

    return "adm/faq.adm";
  }


  /* faqWrite */
  @RequestMapping(value = "adm/faqWrite.do", method = RequestMethod.GET)
  public String faqWrite(Model model, HttpServletRequest request) {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "03" );
    return "adm/faqWrite.adm";
  }

  /* faqInsert */
  @RequestMapping(value = "adm/faqInsert.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String faqInsert(FaqVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */	
    admService.insertFaqList(vo);
    return "{result:'성공'}";
  }

  /* faqModify */
  @RequestMapping(value = "adm/faqModify.do", method = RequestMethod.GET)
  public String faqModify(FaqVO vo, Model model, HttpServletRequest request) throws Exception {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "03" );
    model.addAttribute("list", admService.selectFaq(vo));
    return "adm/faqModify.adm";
  }

  /* faqUpdate */
  @RequestMapping(value = "adm/faqUpdate.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String updateFaq(FaqVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */

    admService.updateFaq(vo);

    return "{result:'성공'}";
  }

  /* faqDelete */
  @RequestMapping(value = "adm/faqDelete.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String faqDelete(FaqVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */

    admService.deleteFaq(vo);

    return "{result:'성공'}";
  }

  /* idea */
  @RequestMapping(value = "adm/idea.do", method = RequestMethod.GET)
  public String idea(PagingVO vo, Model model
      , @RequestParam(value="nowPage", required=false)String nowPage
      , @RequestParam(value="cntPerPage", required=false)String cntPerPage, HttpServletRequest request) throws Exception {
    logger.info("AdmController idea");
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "04" );
    String keyword = vo.getSearch_key();
    String option = vo.getSearch_option();
    int total = admService.countIdeaList(vo);
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
    model.addAttribute("list", admService.selectIdeaList(vo));

    return "adm/idea.adm";
  }

  /* ideaWrite */
  @RequestMapping(value = "adm/ideaWrite.do", method = RequestMethod.GET)
  public String ideaWrite(Model model, HttpServletRequest request) {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "02" );
    String message = request.getParameter("rt");
    model.addAttribute("rt", message);
    return "adm/ideaWrite.adm";
  }

  /* ideaInsert */
  @RequestMapping(value = "adm/ideaInsert.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  public String ideaInsert(IdeaVO vo, HttpServletRequest request, MultipartHttpServletRequest mpRequest) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */
    vo.setWriter(sessionUser.getName());
    admService.insertIdeaList(vo, mpRequest);

    return "redirect:/adm/ideaWrite.do?rt=1";
  }

  /* ideaModify */
  @RequestMapping(value = "adm/ideaModify.do", method = RequestMethod.GET)
  public String ideaModify(IdeaVO vo, Model model, HttpServletRequest request) throws Exception {
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    model.addAttribute("user", sessionUser);
    /* Session Check - END */
    model.addAttribute("gNum", "02" );
    IdeaVO ideaOne = admService.selectIdea(vo);
    model.addAttribute("list", ideaOne);

    List<Map<String, Object>> fileList = admService.selectFileList(ideaOne.getSeq());
    model.addAttribute("file", fileList);
    String message = request.getParameter("rt");
    model.addAttribute("rt", message);
    return "adm/ideaModify.adm";
  }

  /* ideaUpdate */
  @RequestMapping(value = "adm/ideaUpdate.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  public String updateIdea(IdeaVO vo, HttpServletRequest request,  MultipartHttpServletRequest mpRequest) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */

    admService.updateIdea(vo, mpRequest);

    return "redirect:/adm/ideaModify.do?seq="+ vo.getSeq() + "&rt=1";
  }

  /* ideaDelete */
  @RequestMapping(value = "adm/ideaDelete.do", 
      method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
  @ResponseBody
  public String ideaDelete(IdeaVO vo, HttpServletRequest request) throws Exception{
    /* Session Check - START */
    HttpSession session = request.getSession();
    MemberVO sessionUser = (MemberVO) session.getAttribute("loginUser");
    if(sessionUser == null) {
      return "redirect:/adm/login.do?rt=3";
    }
    /* Session Check - END */
    admService.deleteIdea(vo);
    return "{result:'성공'}";
  }

}

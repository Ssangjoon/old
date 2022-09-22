package com.spring.shop.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.HomeController;
import com.spring.shop.login.MemberDto;

@Controller
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/noticeBoard", method = RequestMethod.GET)
    public String noticeBoard(Model model, MemberDto memberDto) {
        logger.info("공지사항!!");

        List<NoticeDto> list = noticeService.list();
        model.addAttribute("noticeList", list);
        model.addAttribute("content", "notice/notice.jsp");

        return "home";
    }

    @RequestMapping(value = "/getNoticeList", method = RequestMethod.GET)
    @ResponseBody
    public List<NoticeDto> noticeList() {
        List<NoticeDto> list = noticeService.list();
        return list;
    }

    @RequestMapping(value = "/getSearch", method = RequestMethod.GET)
    @ResponseBody
    public List<NoticeDto> getSearch(@RequestParam("keyword") String keyword) {
        logger.info("검색 작동");
        List<NoticeDto> list = noticeService.list();
        List<NoticeDto> list2 = new ArrayList<NoticeDto>();

        for (int i = 0; i < list.size(); i++) {
            String writer = list.get(i).getNb_writer();

            if(writer.equals(keyword)) {
                list2.add(list.get(i));
            }
        }

        return list2;
    }

    @RequestMapping(value = "/noticeAddPage", method = RequestMethod.GET)
    public String noticeAddPage(Model model, HttpServletRequest req) {
        logger.info("공지사항 추가 페이지");

        MemberDto loginUser = (MemberDto) req.getSession().getAttribute("loginUser");
        if(loginUser == null) {
            model.addAttribute("MSG", "로그인을 하세요");
            model.addAttribute("content", "member/login.jsp");
            return "home";
        }
        model.addAttribute("content", "notice/noticeAdd.jsp");

        return "home";
    }

    @RequestMapping(value = "/noticeAdd", method = RequestMethod.POST)
    public String noticeAdd(Model model, NoticeDto noticeDto, HttpServletRequest req) {
        logger.info("공지사항 추가");

        MemberDto loginUser = (MemberDto) req.getSession().getAttribute("loginUser");
        noticeDto.setNb_writer(loginUser.getMi_id());
        int result = noticeService.add(noticeDto);

        if(result > 0) {
//			model.addAttribute("MSG", "정상처리 되었습니다");
            return "redirect:/notice/noticeBoard";
        } else {
            model.addAttribute("MSG", "오류 발생");
            model.addAttribute("content", "noticeAdd.jsp");
        }

        return "home";
    }

    @RequestMapping(value = "/noticeDetailPage", method = RequestMethod.GET)
    public String noticeDetailPage(Model model, NoticeDto noticeDto) {
        logger.info("공지사항 상세 페이지");

        NoticeDto boardContent = noticeService.select(noticeDto);

        System.out.println(boardContent.getNb_id());

        model.addAttribute("boardContent", boardContent);
        model.addAttribute("content", "notice/noticeDetail.jsp");


        return "home";
    }

    @RequestMapping(value = "/noticeEdit", method = RequestMethod.POST)
    public String noticeEdit(Model model, NoticeDto noticeDto, HttpServletRequest req) {
        logger.info("공지사항 상세 페이지");

        int result = noticeService.edit(noticeDto);

        if(result > 0) {
            model.addAttribute("MSG", "수정 완료");
            model.addAttribute("content", "notice/notice.jsp");
        } else {
            model.addAttribute("MSG", "수정 실패");
            model.addAttribute("content", "notice/noticeDetail.jsp");
        }

        return "home";
    }

    @RequestMapping(value = "/noticeDelete", method = RequestMethod.GET)
    public String noticeDelete(Model model, NoticeDto noticeDto) {
        logger.info("공지사항 삭제");

        int result = noticeService.delete(noticeDto);

        if(result > 0) {
            return "redirect:/notice/noticeBoard";
        } else {
            model.addAttribute("MSG", "삭제 실패");
            model.addAttribute("content", "notice/noticeDetail.jsp");
        }

        return "home";
    }

}

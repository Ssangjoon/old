package com.spring.shop.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MemberService memberService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        logger.info("로그인 페이지");

        model.addAttribute("content", "member/login.jsp");

        return "home";
    }

    @RequestMapping(value = "/joinIn", method = RequestMethod.POST)
    public String joinIn(Model model, MemberDto memberDto, HttpServletRequest req) {
        logger.info("로그인!!!");

        MemberDto loginUser = memberService.joinIn(memberDto);

        if (loginUser != null) {
            model.addAttribute("content", "main.jsp");
            model.addAttribute("MSG", "로그인 성공");
            req.getSession().setAttribute("loginUser", loginUser);
        }else {
            model.addAttribute("content", "member/login.jsp");
            model.addAttribute("MSG", "가입되지 않은 회원입니다.");
        }

        return "home";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(Model model) {
        logger.info("회원가입 페이지");

        model.addAttribute("content", "member/join.jsp" );

        return "home";
    }

    @RequestMapping(value = "/joinUp", method = RequestMethod.POST)
    public String joinUp(Model model, MemberDto dto) {
        logger.info("회원가입!!");

        int joinUser = memberService.joinMember(dto);

        if(joinUser > 0) {
            model.addAttribute("MSG", "회원가입성공");
        } else {
            model.addAttribute("MSG", "회원가입실패");
        }

        model.addAttribute("content", "member/main.jsp");

        return "home";
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public String userEdit(Model model, MemberDto dto, HttpServletRequest req) {
        logger.info("회원 정보 수정");

        if (dto.getMi_pw() == "") {
            model.addAttribute("MSG", "비밀번호를 기재해주세요");
        } else {
            int result = memberService.userEdit(dto);
            if(result > 0) {
                model.addAttribute("MSG", "회원정보수정 완료");
                MemberDto editedUser = memberService.joinIn(dto);
                req.getSession().setAttribute("loginUser", editedUser);
                model.addAttribute("content", "member/myPage.jsp");
                return "home";
            } else {
                model.addAttribute("MSG", "비밀번호를 확인하세요");
            }
        }

        model.addAttribute("content", "member/myPage.jsp");

        return "home";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(Model model, HttpServletRequest req) {
        logger.info("로그아웃");

        req.getSession().invalidate();

        model.addAttribute("content", "member/main.jsp" );
        model.addAttribute("MSG", "로그아웃 되었습니다");

        return "home";
    }
}

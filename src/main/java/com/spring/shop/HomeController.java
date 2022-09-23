package com.spring.shop;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("메인화면");

        model.addAttribute("content", "main.html");

        return "home";
    }

    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String gallery(Model model) {
        logger.info("갤러리");

        model.addAttribute("content", "gallery.html" );

        return "home";
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String notice(Model model) {
        logger.info("공지사항");

        model.addAttribute("content", "notice.html" );

        return "home";
    }

    @RequestMapping(value = "/myPage", method = RequestMethod.GET)
    public String myPage(Model model) {
        logger.info("마이페이지");

        model.addAttribute("content", "myPage.html" );

        return "home";
    }
}

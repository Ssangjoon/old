package com.bts.yomojomo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.bts.yomojomo.conf.MvcConfiguration;
import com.bts.yomojomo.controller.ResultMap;
import com.bts.yomojomo.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;

// Authentication(인증), Authorization(권한검사) -> 인증과 권한 검사 Auth
// 사용자 인증 여부를 검사하는 인터셉터
public class Authinterceptor implements HandlerInterceptor {
  private static final Logger log = LoggerFactory.getLogger(MvcConfiguration.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    log.debug("preHandle() 호출됨!");

    //로그인 여부 검사
    HttpSession session = request.getSession(); //http session 객체를 얻어온다.
    Member loginUser = (Member) session.getAttribute("loginUser"); //session 객체에서 loginUser로 저장된 member 객체를 꺼낸다.

    if (loginUser == null) {
      // 로그인을 하지 않았으면 오류 메시지를 JSON 형식으로 직접 응답한다.
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(new ObjectMapper().writeValueAsString(new ResultMap()
          .setStatus(ResultMap.FAIL)
          .setData("로그인 하지 않았습니다!")));

      return false; // 페이지 컨트롤러를 실행하지 말고 즉시 응답하라!
    }

    return true; // 로그인 된 상태라면, 계속 진행하라! (요청한 페이지 컨트롤러의 메서드를 호출하라!)
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    log.trace("postHandle() 호출됨!");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}

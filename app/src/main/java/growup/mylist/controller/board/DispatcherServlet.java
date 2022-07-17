package growup.mylist.controller.board;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/app/*")
//@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet{
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String controllerPath = request.getPathInfo();

    try {
      response.setContentType("text/html; charset=UTF-8");
      RequestDispatcher 요청배달자 = request.getRequestDispatcher(controllerPath);
      요청배달자.include(request, response);


      // 페이지 컨트롤러 실행 후 발생한 에러 
      Exception exception = (Exception) request.getAttribute("exception");
      if(exception != null) {
        throw exception;
      }
      String viewUrl = (String) request.getAttribute("viewUrl");
      if (viewUrl.startsWith("redirect:")) { // 예) redirect:list
        response.sendRedirect(viewUrl.substring(9)); // 예) list
      }else {
        request.getRequestDispatcher(viewUrl).include(request, response);
      }
      request.getRequestDispatcher(viewUrl).include(request, response);

    } catch (Exception e) { // 페이지 컨트롤러 실행 전 발생한 에러 즉, 클라이언트가 요청한 url이 유효하지 않은 경우
      if(request.getAttribute("exception") == null) {
        request.setAttribute("exception", e);
      }
      request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
  }
}

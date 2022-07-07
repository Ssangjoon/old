package growup.board;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

// 서블릿 컨테이너가 실행할 클래스를 만드려면 
// Servlet API규칙에 따라 작성해야 한다. 
//
@WebServlet("/hello") // 서블릿 컨테이너에게 이 클래스가 /hello 요청을 처리하는 서블릿임을 알려준다. 
public class HelloServlet implements Servlet{

  ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("init() 호출!!");
    this.config = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    System.out.println("getServletConfig() 호출!");
    return this.config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    System.out.println("service() 호출");

    //HTTP클라이언트가 name이라는 이름으로 보내 온 파라티터 값을 읽는다. 
    String name = req.getParameter("name");

    //HTTP클라이언트에게 보낼 MIME 타입을 설정한다. 
    res.setContentType("text/plain;charset=UTF-8");

    //HTTP클라이언트에게 콘텐트를 출력할 도구를 준비한다. 
    PrintWriter out = res.getWriter();

    //HTTP클라이언트에게 콘텐트를 출력한다. 
    out.printf("%s님 환영합니다!", name);
  }

  @Override
  public String getServletInfo() {
    System.out.println("getServletInfo() 호출");
    return "HelloServlet";
  }

  @Override
  public void destroy() {
    System.out.println("destroy() 호출");
  }

}

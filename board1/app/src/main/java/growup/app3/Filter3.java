package growup.app3;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// 서블릿 실행 전/후에 수행할 작업이 있다면 필터에서 처리하라. 
//
@WebFilter({"/hello3","/hello5"}) //어떤 요청에 대해 필터를 적용할 것인지 지정한다. 
public class Filter3 implements Filter{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // TODO Auto-generated method stub
    Filter.super.init(filterConfig);
    System.out.println("Filter3.init()호출");
  }
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // TODO Auto-generated method stub
    System.out.println("Filter3.doFilter() 호출");

    // 형변환은 실제로 넘어오는 객체가 HttpServletRequest이기에 형변환이 가능하다. 아니라면 오류.
    // 굳이 형변환을 해야 하는 이유는... 서블릿의 처음 목적이 웹?이 아니었기 때문에 . 
    HttpServletRequest httpRequest = (HttpServletRequest)request; 
    System.out.println(httpRequest.getServletPath());

    // 다음 필터 실행. 없으면 서블릿 실행. 
    chain.doFilter(request, response);
  }
  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    Filter.super.destroy();
    System.out.println("Filter3.destroy() 호출");
  }
}

package growup.mylist.controller.board;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

// 서블릿 컨테이너가 실행할 클래스를 만드려면 
//@SuppressWarnings("serial")
// Servlet API규칙에 따라 작성해야 한다. 
//
//@WebServlet("/board/list") // 서블릿 컨테이너에게 이 클래스가 /hello 요청을 처리하는 서블릿임을 알려준다. 
public class BoardListController extends HttpServlet{

  BoardService boardService;

  @Override
  public void init() throws ServletException {
    // BoardService 객체를 웹애플리케이션 보관소에서 꺼낸다.
    ServletContext 웹애플리케이션보관소 = this.getServletContext();
    boardService = (BoardService) 웹애플리케이션보관소.getAttribute("boardService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      // 1) 입력 데이터 가공 및 검증
      int pageNo = 1; 
      int pageSize = 5; 
      int totalPageSize = 0;

      try{ // pageSize 파라미터 값이 있다면 기본 값을 변경한다. 
        pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if (pageSize < 5 || pageSize >100){
          pageSize = 5;  // 유저가 페이지 크기를 의도적으로 큰 값을 입력한다면 서버에 과부하가..
        }
      } catch (Exception e) {}

      // 게시글 전체 개수를 알아내서 페이지 개수를 계산한다.
      int boardSize = boardService.size();
      totalPageSize = boardSize / pageSize;  // 예: 게시글개수 / 페이지당개수 = 16 / 5 = 3 
      if ((boardSize % pageSize) > 0){
        totalPageSize++;
      }

      try { // pageNo 파라미터 값이 있다면 기본 값을 변경한다. 
        pageNo = Integer.parseInt(request.getParameter("pageNo"));
        // pageNo 유효성 검증
        if (pageNo < 1 || pageNo > totalPageSize) {
          pageNo = 1;
        }
      } catch (Exception e) {}

      // 2) 서비스 객체 실행 
      List<Board> boards = boardService.list(totalPageSize, pageNo);

      // 3) 출력 데이터 준비
      request.setAttribute("list", boards);
      request.setAttribute("pageNo", pageNo);
      request.setAttribute("pageSize", pageSize);
      request.setAttribute("totalPageSize", totalPageSize);

      // 4) 뷰 객체에게 실행을 위임한다. 
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/jsp/board/list.jsp");
      요청배달자.forward(request, response);
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
  }
}




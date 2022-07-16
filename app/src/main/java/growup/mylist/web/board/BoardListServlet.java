package growup.mylist.web.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

// 서블릿 컨테이너가 실행할 클래스를 만드려면 
@SuppressWarnings("serial")
// Servlet API규칙에 따라 작성해야 한다. 
//
@WebServlet("/board/list0") // 서블릿 컨테이너에게 이 클래스가 /hello 요청을 처리하는 서블릿임을 알려준다. 
public class BoardListServlet extends HttpServlet{

  BoardService boardService;

  @Override
  public void init() throws ServletException {
    // BoardService 객체를 웹애플리케이션 보관소에서 꺼낸다.
    ServletContext 웹애플리케이션보관소 = this.getServletContext();
    boardService = (BoardService) 웹애플리케이션보관소.getAttribute("boardService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset=\"UTF-8\">");
    out.println("  <title>MyList!</title>");
    out.println("  <link href=\"/css/common.css\" rel=\"stylesheet\">");
    out.println("</head>");
    out.println("<body>");

    out.println("<div class=\"container\">");

    out.println("<div id=\"header\">");
    RequestDispatcher 요청배달자 = req.getRequestDispatcher("/header");
    요청배달자.include(req, resp);
    out.println("</div>");

    out.println("<div id=\"sidebar\">");
    req.getRequestDispatcher("/sidebar").include(req, resp);
    out.println("</div>");

    out.println("<div id=\"content\">");
    out.println("<h1>게시글</h1>");
    out.println("<a href=\"add\">새 게시글</a>");
    out.println("<table id=\"x-board-table\" border=\"1\">");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>작성자</th>");
    out.println("    <th>조회수</th>");
    out.println("    <th>등록일</th>");
    out.println("  </tr>");
    out.println("</thead>");
    out.println("<tbody>");

    List<Board> boards = boardService.list();
    for (Board board : boards) {
      out.println("  <tr>");
      out.printf("    <td>%d</td>\n", board.getNo());
      out.printf("    <td><a href='detail?no=%d'>%s</a></td>\n", board.getNo(), board.getTitle());
      out.printf("    <td>%s</td>\n", board.getWriter().getName());
      out.printf("    <td>%d</td>\n", board.getViewCount());
      out.printf("    <td>%s</td>\n", board.getCreatedDate());
      out.println("  </tr>");
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("</div>");

    out.println("<div id=\"footer\">");
    req.getRequestDispatcher("/footer").include(req, resp);
    out.println("</div>");

    out.println("</div>");

    out.println("</body>");
    out.println("</html>");

  }
}




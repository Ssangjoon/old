package growup.mylist.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.controller.RequestMapping;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

//@Component("/board/detail")
public class BoardDetailController4   {

  BoardService boardService;
  public BoardDetailController4(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardService.get(no);
    request.setAttribute("board", board);
    return "/jsp/board/detail.jsp";

  }
}

package growup.mylist.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.controller.Controller;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

public class BoardDetailController3 implements Controller {

  BoardService boardService;
  public BoardDetailController3(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardService.get(no);
    request.setAttribute("board", board);
    return "/jsp/board/detail.jsp";

  }
}

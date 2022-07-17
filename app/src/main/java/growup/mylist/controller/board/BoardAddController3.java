package growup.mylist.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.controller.Controller;
import growup.mylist.domain.Board;
import growup.mylist.domain.Member;
import growup.mylist.service.BoardService;

public class BoardAddController3 implements Controller{

  BoardService boardService;

  public BoardAddController3(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if(request.getMethod().equals("GET")) {
      return "/jsp/board/form.jsp";
    }

    Board board = new Board();
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    board.setWriter(loginUser);

    boardService.add(board);

    return"redirect:list";

  }
}













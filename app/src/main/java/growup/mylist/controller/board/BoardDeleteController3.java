package growup.mylist.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.controller.Component;
import growup.mylist.controller.Controller;
import growup.mylist.domain.Board;
import growup.mylist.domain.Member;
import growup.mylist.service.BoardService;

@Component("/board/delete")
public class BoardDeleteController3 implements Controller{

  BoardService boardService;

  public BoardDeleteController3(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    board.setWriter(loginUser);

    boardService.delete(board);

    return  "redirect:list";


  }
}

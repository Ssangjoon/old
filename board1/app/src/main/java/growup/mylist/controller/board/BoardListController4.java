package growup.mylist.controller.board;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import growup.mylist.controller.RequestMapping;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

//@Component("/board/list")
public class BoardListController4 {

  BoardService boardService;

  public BoardListController4(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

    return "/jsp/board/list.jsp";

  } 
}





package growup.mylist.service;

import java.util.List;
import growup.mylist.domain.Board;

public interface BoardService {

  int add(Board board);

  List<Board> list();

  Board get(int no);

  int update(Board board);

  int delete(Board board);
}








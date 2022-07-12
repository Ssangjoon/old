package growup.mylist.service.impl;

import java.util.List;
import growup.mylist.dao.BoardDao;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

public class DefaultBoardService implements BoardService {

  BoardDao boardDao;

  @Override
  public int add(Board board) {
    return boardDao.insert(board);
  }

  @Override
  public List<Board> list() {
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) {
    Board board = boardDao.findByNo(no);
    if (board != null) {
      boardDao.increaseViewCount(no);
    }
    return board;
  }

  @Override
  public int update(Board board) {
    return boardDao.update(board);
  }

  @Override
  public int delete(Board board) {
    return boardDao.delete(board);
  }
}








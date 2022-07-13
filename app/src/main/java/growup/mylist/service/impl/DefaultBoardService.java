package growup.mylist.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import growup.mylist.dao.BoardDao;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

public class DefaultBoardService implements BoardService {

  SqlSessionFactory sqlSessionFactory;

  public DefaultBoardService(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int add(Board board) {
    // 주의!
    // - 스레드 마다 Sqlsession이 구분되어야 한다. 즉 클라이언트 간의 트랜잭션이 분리되어야 한다. 
    // - 따라서 스레드가 서비스 메서드를 호출하는 시점에서 Sqlsession을 얻어 DAO를 준비해야 한다.
    // 세션을 클라이언트와 상관없이 공유하게 되면 ... 전체가 커밋이 되어버린다.? 
    SqlSession session = sqlSessionFactory.openSession(); 
    BoardDao boardDao = session.getMapper(BoardDao.class);
    return boardDao.insert(board);
  }

  @Override
  public List<Board> list() {
    SqlSession session = sqlSessionFactory.openSession(); 
    BoardDao boardDao = session.getMapper(BoardDao.class);
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) {
    SqlSession session = sqlSessionFactory.openSession(); 
    BoardDao boardDao = session.getMapper(BoardDao.class);
    Board board = boardDao.findByNo(no);
    if (board != null) {
      boardDao.increaseViewCount(no);
    }
    return board;
  }

  @Override
  public int update(Board board) {
    SqlSession session = sqlSessionFactory.openSession(); 
    BoardDao boardDao = session.getMapper(BoardDao.class);
    return boardDao.update(board);
  }

  @Override
  public int delete(Board board) {
    SqlSession session = sqlSessionFactory.openSession(); 
    BoardDao boardDao = session.getMapper(BoardDao.class);
    return boardDao.delete(board);
  }
}








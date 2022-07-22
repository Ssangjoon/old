package growup.mylist.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import growup.mylist.dao.BoardDao;
import growup.mylist.domain.Board;
import growup.mylist.service.BoardService;

@Service
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
    try (SqlSession session = sqlSessionFactory.openSession();) { 
      BoardDao boardDao = session.getMapper(BoardDao.class);
      int count = boardDao.insert(board);
      session.commit();
      return count;

    } catch (RuntimeException e) {
      throw e;
    }
  }

  @Override
  public List<Board> list(int pageSize, int pageNo) {
    try(SqlSession session = sqlSessionFactory.openSession();){ // 결과를 받고나면 가비지가 되지만 connection 은 살아있다 -> 끊어 줘야 함
      BoardDao boardDao = session.getMapper(BoardDao.class);
      return boardDao.findAll(pageSize, (pageSize * (pageNo-1)));
    } catch (RuntimeException e) {
      throw e;
    }
  }

  @Override
  public Board get(int no) {
    try (SqlSession session = sqlSessionFactory.openSession();) {
      BoardDao boardDao = session.getMapper(BoardDao.class);
      Board board = boardDao.findByNo(no);
      if (board != null) {
        boardDao.increaseViewCount(no);
      }
      session.commit();
      return board;

    } catch (RuntimeException e) {
      throw e;
    }
  }

  @Override
  public int update(Board board) {
    try (SqlSession session = sqlSessionFactory.openSession();) {
      BoardDao boardDao = session.getMapper(BoardDao.class);
      int count = boardDao.update(board);
      session.commit();
      return count;

    } catch (RuntimeException e) {
      throw e;
    }
  }

  @Override
  public int delete(Board board) {
    try (SqlSession session = sqlSessionFactory.openSession();) {
      BoardDao boardDao = session.getMapper(BoardDao.class);
      int count = boardDao.delete(board);
      session.commit();
      return count;

    } catch (RuntimeException e) {
      throw e;
    }
  }

  @Override
  public int size() {
    try(SqlSession session = sqlSessionFactory.openSession();){
      BoardDao boardDao = session.getMapper(BoardDao.class);
      return boardDao.countAll();
    } catch (RuntimeException e) {
      throw e;
    }
  }
}




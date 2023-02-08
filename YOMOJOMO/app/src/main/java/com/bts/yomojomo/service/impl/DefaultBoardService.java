package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.BoardDao;
import com.bts.yomojomo.domain.Board;
import com.bts.yomojomo.service.BoardService;

//서비스객체 -> 업무로직, 트랜잭션 제어 
// => 일관성을 위해 반드시 서비스 객체를 통해 DAO를 사용한다. 
@Service // Spring IoC 컨테이너가 객체를 만들어 저장할 때 클래스 이름을 사용한다. 예) defaultBoardService
public class DefaultBoardService implements BoardService {

  @Autowired
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
  public List<Board> findByGroupNo(int no) {
    return boardDao.findByGroupNo(no);
  }

  @Override
  public List<Board> findByGroupNoAsc(int no) {
    return boardDao.findByGroupNoAsc(no);
  }

  @Override
  public List<Board> findByBoardNo(int no) {
    List<Board> board = boardDao.findByBoardNo(no);
    if (board != null) {
      boardDao.increaseViewCount(no);
    }
    return board;
  }

  @Override
  public Board get(int no) {
    return boardDao.findByNo(no);
  }

  @Override
  public int update(Board board) {
    return boardDao.update(board);
  }

  @Override
  public int delete(int no) {
    return boardDao.delete(no);
  }

  @Override
  public int increaseViewCount() {
    return boardDao.increaseViewCount();
  }


}

package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Board;

public interface BoardService {

  int add(Board board);

  List<Board> list();

  List<Board> findByGroupNo(int no);

  List<Board> findByGroupNoAsc(int no);

  List<Board> findByBoardNo(int no);

  Board get(int no);

  int update(Board board);

  int delete(int no);

  int increaseViewCount();
}

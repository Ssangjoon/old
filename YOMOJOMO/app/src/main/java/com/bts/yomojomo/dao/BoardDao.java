package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.Board;

@Mapper 

public interface BoardDao {
  int countAll();

  int insert(Board board);

  List<Board> findByGroupNo(int no);

  List<Board> findByGroupNoAsc(int no);

  List<Board> findByBoardNo(int no);

  Board findByNo(int no);

  int update(Board board);

  int delete(int no);

  int increaseViewCount(int no);

  int increaseViewCount();

  List<Board> findAll();
}

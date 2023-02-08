package com.bts.yomojomo.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.ApplyAnswer;

@Mapper 
public interface ApplyAnswerDao {
  int countAll();

  List<ApplyAnswer> findAll();

  int insert(@Param("answerList") ArrayList<ApplyAnswer> answerList);

  //  int insert(ApplyAnswer applyAnswer);

  ApplyAnswer findByNo(int no);

  int update(ApplyAnswer applyAnswer);

  int delete(int no);

}

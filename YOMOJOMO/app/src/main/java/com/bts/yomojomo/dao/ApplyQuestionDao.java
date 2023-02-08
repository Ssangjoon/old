package com.bts.yomojomo.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.ApplyQuestion;

@Mapper 
public interface ApplyQuestionDao {
  int insert(@Param("questionList") ArrayList<ApplyQuestion> questionList);

  int update(@Param("questionList") ArrayList<ApplyQuestion> questionList);

  int countAll();

  List<ApplyQuestion> findAll();

  List<ApplyQuestion> findQuestion(int no);

  ApplyQuestion findByNo(int no);

  int count(int no);

  int delete();
}

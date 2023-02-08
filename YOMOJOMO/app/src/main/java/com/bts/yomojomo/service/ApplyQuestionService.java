package com.bts.yomojomo.service;

import java.util.ArrayList;
import java.util.List;
import com.bts.yomojomo.domain.ApplyQuestion;

public interface ApplyQuestionService {

  int add(ArrayList<ApplyQuestion> questionList);

  List<ApplyQuestion> list();

  List<ApplyQuestion> findQuestion(int no);

  //  ApplyQuestion get(int no);
  //
  //  int update(ApplyQuestion applyQuestion);

  int count(int no);

  int update(ArrayList<ApplyQuestion> questionList);

}

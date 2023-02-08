package com.bts.yomojomo.service;

import java.util.ArrayList;
import java.util.List;
import com.bts.yomojomo.domain.ApplyAnswer;

public interface ApplyAnswerService {

  //  int add(ApplyAnswer applyAnswer);

  List<ApplyAnswer> list();

  int add(ArrayList<ApplyAnswer> answerList);

  //  ApplyAnswer get(int no);
  //
  //  int update(ApplyAnswer applyAnswer);
  //
  //  int delete(int no);

}

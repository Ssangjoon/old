package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.ApplyAnswer;
import com.bts.yomojomo.domain.ApplyFixedAnswer;

public interface ApplyFixedAnswerService {
  int add(ApplyFixedAnswer applyFixedAnswer);

  int count(int no);

  List<ApplyAnswer> findRequestByMasNO(ApplyFixedAnswer applyFixedAnswer);
}


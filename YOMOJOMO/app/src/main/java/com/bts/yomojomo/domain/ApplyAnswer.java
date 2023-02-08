package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplyAnswer {
  String answer;
  int applyQuestionNo; // appl_question_no 가입신청서 질문목록 번호 
  Date applyDate;// appl_dt
  Member writer;


  public ApplyAnswer(int applyQuestionNo, String answer) {
    this.applyQuestionNo = applyQuestionNo;
    this.answer = answer;
  }
  public ApplyAnswer() {}
}

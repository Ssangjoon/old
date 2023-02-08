package com.bts.yomojomo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplyQuestion {
  int no; // appl_question_no 질문목록 번호 
  int groupNo; //g_no 가입신청서 번호
  String questionName; //question_name 질문이름

  public ApplyQuestion(int no, String questionName) {
    this.no = no;
    this.questionName = questionName;
  }
  public ApplyQuestion() {}
}

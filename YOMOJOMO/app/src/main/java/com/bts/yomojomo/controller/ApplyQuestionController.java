package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.ApplyQuestion;
import com.bts.yomojomo.service.ApplyQuestionService;

@RestController
public class ApplyQuestionController {
  @Autowired
  ApplyQuestionService applyQuestionService;

  //신청서 질문 등록 
  @RequestMapping("/applyQuestion/add")
  public Object add(int no, String[] questionName) {
    ArrayList<ApplyQuestion> questionList = new ArrayList();
    for (int i = 0; i < questionName.length; i++) {
      if (questionName[i].length() == 0) {
        continue;
      }
      ApplyQuestion applyQuestion = new ApplyQuestion();
      applyQuestion.setQuestionName(questionName[i]);
      applyQuestion.setGroupNo(no);
      questionList.add(applyQuestion);
      System.out.println(questionList);
    }
    return new ResultMap().setStatus(SUCCESS).setData(applyQuestionService.add(questionList));  
  }
  //신청서의 질문 목록을 조회
  @RequestMapping("/applyQuestion/findQuestion")
  public Object findQuestion(int no) {
    List<ApplyQuestion> applyquestion= applyQuestionService.findQuestion(no);
    if (applyquestion == null) {
      return new ResultMap().setStatus(FAIL).setData("해당되는 신청서가 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(applyquestion); 
  }
  //신청서 수정
  @RequestMapping("/applyQuestion/update")
  public Object update(String[] questions) {
    ArrayList<ApplyQuestion> questionList = new ArrayList<>();

    //    ApplyQuestion ApplyQuestion = new ApplyQuestion()
    for (int i=0; i < questions.length; i++) {
      String[] value = questions[i].split("_");
      ApplyQuestion applyQuestion = new ApplyQuestion(Integer.parseInt(value[0]),value[1]);
      questionList.add(applyQuestion);
    }
    System.out.println(questionList);
    return new ResultMap().setData(applyQuestionService.update(questionList));    
  }

  @RequestMapping("/applyQuestion/count")
  public Object count(int no) {
    return new ResultMap().setStatus(SUCCESS).setData(applyQuestionService.count(no));
  }


  //  @RequestMapping("/applayQuestion/get")
  //  public Object get(int no) {
  //    ApplyQuestion applyQuestion = applyQuestionService.get(no);
  //    if (applyQuestion == null) {
  //      return new ResultMap().setStatus(FAIL).setData("해당번호의 신청서가 없습니다.");
  //    }
  //    return new ResultMap().setStatus(SUCCESS).setData(applyQuestion);
  //  }
  //
  //  @RequestMapping("/applayQuestion/list")
  //  public Object list() {
  //    return applyQuestionService.list(); 
  //  }
  //
  //
}
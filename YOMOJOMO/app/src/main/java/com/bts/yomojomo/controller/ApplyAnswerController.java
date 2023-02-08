package com.bts.yomojomo.controller;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.ApplyAnswer;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.service.ApplyAnswerService;

@RestController
public class ApplyAnswerController {
  @Autowired
  ApplyAnswerService applyAnswerService;

  @RequestMapping("/applyAnswer/add")
  public Object add(String[] answers, HttpSession session) {
    ArrayList<ApplyAnswer> answerList = new ArrayList<>();
    for (int i = 0; i < answers.length; i++) {
      String[] value = answers[i].split("_");
      if (value[1].length() == 0) {
        continue;
      }
      ApplyAnswer applyAnswer = new ApplyAnswer(Integer.parseInt(value[0]),value[1]);
      //작성자 정보
      Member member = (Member) session.getAttribute("loginUser");
      applyAnswer.setWriter(member);
      System.out.println(member);
      answerList.add(applyAnswer);
    }
    System.out.println(answerList);
    return new ResultMap().setStatus(SUCCESS).setData(applyAnswerService.add(answerList));  
  }


  //  @RequestMapping("/applydefault/add")
  //  public Object add() {
  //    return applyAnswerService.list(); 
  //  }

  @RequestMapping("/applyAnswer/list")
  public Object list() {
    return applyAnswerService.list(); 
  }
  //
  //  @RequestMapping("/applyAnswer/update")
  //  public Object update(ApplyAnswer applyAnswer) {
  //    return applyAnswerService.update(applyAnswer);
  //  }
  //
  //  @RequestMapping("/applyAnswer/delete")
  //  public Object delete(int no) {
  //    return applyAnswerService.delete(no);
  //  }
}

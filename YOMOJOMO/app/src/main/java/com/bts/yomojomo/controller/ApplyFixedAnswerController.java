package com.bts.yomojomo.controller;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.ApplyFixedAnswer;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.service.ApplyFixedAnswerService;

@RestController
public class ApplyFixedAnswerController {
  @Autowired
  ApplyFixedAnswerService applyFixedAnswerService;

  @RequestMapping("/applyFixedAnswer/add")
  public Object add(ApplyFixedAnswer applyFixedAnswer, HttpSession session) {
    //작성자 정보
    Member member = (Member) session.getAttribute("loginUser");
    applyFixedAnswer.setWriter(member);
    System.out.println(applyFixedAnswer);

    return new ResultMap().setStatus(SUCCESS).setData(applyFixedAnswerService.add(applyFixedAnswer));  
  }


  @RequestMapping("/applydefault/count")
  public Object count(int gno) {
    return new ResultMap().setStatus(SUCCESS).setData(applyFixedAnswerService.count(gno));   
  }

  //  @RequestMapping("/applydefault/add")
  //  public Object add() {
  //    return applyFixedAnswerService.list(); 
  //  }

  @RequestMapping("/applyFixedAnswer/findRequestByMasNO")
  public Object findRequestByMasNO(ApplyFixedAnswer applyFixedAnswer) {
    return new ResultMap().setStatus(SUCCESS).setData(applyFixedAnswerService.findRequestByMasNO(applyFixedAnswer)); 
  }
  //
  //  @RequestMapping("/applyFixedAnswer/update")
  //  public Object update(ApplyFixedAnswer applyFixedAnswer) {
  //    return applyFixedAnswerService.update(applyFixedAnswer);
  //  }
  //
  //  @RequestMapping("/applyFixedAnswer/delete")
  //  public Object delete(int no) {
  //    return applyFixedAnswerService.delete(no);
  //  }
}

package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Comment;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.service.CommentService;

@RestController
public class CommentController {
  @Autowired
  CommentService commentService;

  @RequestMapping("/comment/add")
  public Object add(Comment comment,HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    comment.setWriter(member);
    return new ResultMap().setStatus(SUCCESS).setData(commentService.add(comment));
  }
  //
  //  @RequestMapping("/comment/get")
  //  public Object get(int no) {
  //    return commentService.findByNo(no);
  //  }
  //
  //  @RequestMapping("/comment/list")
  //  public Object list() {
  //    return commentService.findAll(); 
  //  }
  //
  @RequestMapping("/comment/update")
  public Object update(Comment comment,HttpSession session ) {
    Member member = (Member) session.getAttribute("loginUser");
    comment.setWriter(member);
    // 서비스 객체 실행
    int count = commentService.update(comment);
    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("댓글 번호가 유효하지 않거나 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/comment/delete")
  public Object delete(int no) {
    return commentService.delete(no);
  }
}

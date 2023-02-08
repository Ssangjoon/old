package com.bts.yomojomo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.CommentDao;
import com.bts.yomojomo.domain.Comment;
import com.bts.yomojomo.service.CommentService;

//서비스객체 -> 업무로직, 트랜잭션 제어 
// => 일관성을 위해 반드시 서비스 객체를 통해 DAO를 사용한다. 
@Service // Spring IoC 컨테이너가 객체를 만들어 저장할 때 클래스 이름을 사용한다. 예) defaultCommentService
public class DefaultCommentService implements CommentService {

  @Autowired
  CommentDao commentDao;

  @Override
  public int add(Comment comment) {
    return commentDao.insert(comment);
  }

  //  @Override
  //  public List<Comment> list() {
  //    return commentDao.findAll();
  //  }


  //  @Override
  //  public Comment get(int no) {
  //    Comment comment = commentDao.findByNo(no);
  //    if (comment != null) {
  //      commentDao.increaseViewCount(no);
  //    }
  //    return comment;
  //  }
  //
  @Override
  public int update(Comment comment) {
    return commentDao.update(comment);
  }

  @Override
  public int delete(int no) {
    return commentDao.delete(no);
  }
  //
  //  @Override
  //  public int increaseViewCount() {
  //    return commentDao.increaseViewCount();
  //  }


}

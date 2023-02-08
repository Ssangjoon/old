package com.bts.yomojomo.service;

import com.bts.yomojomo.domain.Comment;

public interface CommentService {

  int add(Comment comment);

  //  List<Comment> list();
  //
  //  List<Comment> findByGroupNo(int no);
  //
  //  List<Comment> findByCommentNo(Comment comment);
  //
  //  Comment get(int no);
  //
  int update(Comment comment);
  //
  int delete(int no);
  //
  //  int increaseViewCount();
}

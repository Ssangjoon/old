package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.Comment;

@Mapper 
public interface CommentDao {
  int countAll();

  List<Comment> findAll();

  int insert(Comment comment);

  Comment findByNo(int no);

  int update(Comment comment);

  int delete(int no);
}

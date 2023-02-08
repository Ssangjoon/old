package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.ApplyAnswer;
import com.bts.yomojomo.domain.ApplyFixedAnswer;

@Mapper 
public interface ApplyFixedAnswerDao {
  //  int countAll();
  //
  List<ApplyAnswer> findRequestByMasNO(ApplyFixedAnswer applyFixedAnswer);

  int insert(ApplyFixedAnswer applyFixedAnswer);

  int count(int no);


  //  ApplyAnswer findByNo(int no);
  //
  //  int update(ApplyAnswer applyAnswer);
  //
  //  int delete(int no);

}

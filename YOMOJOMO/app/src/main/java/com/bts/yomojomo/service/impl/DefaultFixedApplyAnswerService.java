package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.ApplyFixedAnswerDao;
import com.bts.yomojomo.domain.ApplyAnswer;
import com.bts.yomojomo.domain.ApplyFixedAnswer;
import com.bts.yomojomo.service.ApplyFixedAnswerService;

//서비스객체 -> 업무로직, 트랜잭션 제어 
// => 일관성을 위해 반드시 서비스 객체를 통해 DAO를 사용한다. 
@Service // Spring IoC 컨테이너가 객체를 만들어 저장할 때 클래스 이름을 사용한다. 예) defaultBoardService
public class DefaultFixedApplyAnswerService implements ApplyFixedAnswerService {

  @Autowired
  ApplyFixedAnswerDao applyFixedAnswerDao;


  @Override
  public int add(ApplyFixedAnswer applyFixedAnswer) {
    return applyFixedAnswerDao.insert(applyFixedAnswer);
  }

  @Override
  public int count(int no) {
    return applyFixedAnswerDao.count(no);
  }


  @Override
  public List<ApplyAnswer> findRequestByMasNO(ApplyFixedAnswer applyFixedAnswer) { 
    return applyFixedAnswerDao.findRequestByMasNO(applyFixedAnswer);
  }

  //  @Override
  //  public ApplyAnswer get(int no) {
  //    return applyAnswerDao.findByNo(no);
  //  }
  //
  //  @Override
  //  public int update(ApplyAnswer applyAnswer) {
  //    return applyAnswerDao.update(applyAnswer);
  //  }
  //
  //  @Override
  //  public int delete(int no) {
  //    return applyAnswerDao.delete(no);
  //  }



}

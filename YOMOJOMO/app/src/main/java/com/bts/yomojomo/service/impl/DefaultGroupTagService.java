package com.bts.yomojomo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.ApplyQuestionDao;
import com.bts.yomojomo.service.GroupTagService;

//서비스객체 -> 업무로직, 트랜잭션 제어 
// => 일관성을 위해 반드시 서비스 객체를 통해 DAO를 사용한다. 
@Service // Spring IoC 컨테이너가 객체를 만들어 저장할 때 클래스 이름을 사용한다. 예) defaultBoardService
public class DefaultGroupTagService implements GroupTagService {

  @Autowired
  ApplyQuestionDao applyQuestionDao;


  //  @Override
  //  public int add(ApplyQuestion applyQuestion) {
  //    return applyQuestionDao.insert(applyQuestion);
  //  }

  //  @Override
  //  public List<ApplyQuestion> list() {
  //    return applyQuestionDao.findAll();
  //  }
  //
  //  @Override
  //  public ApplyQuestion get(int no) {
  //    return applyQuestionDao.findByNo(no);
  //  }
  //
  //  @Override
  //  public int update(ApplyQuestion applyQuestion) {
  //    return applyQuestionDao.update(applyQuestion);
  //  }
  //
  //  @Override
  //  public int delete(int no) {
  //    return applyQuestionDao.delete(no);
  //  }

}

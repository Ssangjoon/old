package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.MembGradeDao;
import com.bts.yomojomo.domain.MembGrade;

@RestController
public class MembGradeController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  MembGradeDao membGradeDao;

  @RequestMapping("/membGrade/add")
  public Object add(MembGrade membGrade) {
    return membGradeDao.insert(membGrade);
  }

  @RequestMapping("/membGrade/get")
  public Object get(int no) {
    return membGradeDao.findByNo(no);
  }

  @RequestMapping("/membGrade/list")
  public Object list() {
    return membGradeDao.findAll(); 
  }

  @RequestMapping("/membGrade/update")
  public Object update(MembGrade membGrade) {
    return membGradeDao.update(membGrade);
  }

  @RequestMapping("/membGrade/delete")
  public Object delete(int no) {
    return membGradeDao.delete(no);
  }
}

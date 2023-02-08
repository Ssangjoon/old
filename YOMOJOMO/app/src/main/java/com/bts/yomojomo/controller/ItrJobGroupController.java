package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.ItrJobGroupDao;
import com.bts.yomojomo.domain.ItrJobGroup;


@RestController
public class ItrJobGroupController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  ItrJobGroupDao itrJobGroupDao;

  @RequestMapping("/itrjobgroup/add")
  public int add(ItrJobGroup itrJobGroup) {
    return itrJobGroupDao.insert(itrJobGroup);
  }

  @RequestMapping("/itrjobgroup/get")
  public Object get(int no) {
    return itrJobGroupDao.findByNo(no);
  }

  @RequestMapping("/itrjobgroup/list")
  public Object list() {
    return itrJobGroupDao.findAll(); 
  }

  @RequestMapping("/itrjobgroup/update")
  public int update(ItrJobGroup itrJobGroup) {
    return itrJobGroupDao.update(itrJobGroup);
  }

  @RequestMapping("/itrjobgroup/delete")
  public Object delete(int no) {
    return itrJobGroupDao.delete(no);
  }
}
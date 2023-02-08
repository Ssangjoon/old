package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.GroupTagDao;
import com.bts.yomojomo.domain.GroupTag;


@RestController
public class GroupTagController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  GroupTagDao groupTagDao;

  @RequestMapping("/grouptag/add")
  public int add(GroupTag groupTag) {

    return groupTagDao.insert(groupTag);
  }

  //  @RequestMapping("/grouptag/get")
  //  public Object get(int no) {
  //    return groupTagDao.findByNo(no);
  //  }
  //
  //  @RequestMapping("/grouptag/list")
  //  public Object list() {
  //    return groupTagDao.findAll(); 
  //  }
  //
  //  @RequestMapping("/grouptag/update")
  //  public int update(GroupTag groupTag) {
  //    return groupTagDao.update(groupTag);
  //  }
  //
  //  @RequestMapping("/grouptag/delete")
  //  public Object delete(int no) {
  //    return groupTagDao.delete(no);
  //  }
}
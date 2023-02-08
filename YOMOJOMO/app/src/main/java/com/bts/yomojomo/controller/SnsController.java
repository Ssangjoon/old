package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.SnsDao;
import com.bts.yomojomo.domain.Sns;

@RestController
public class SnsController {
  @Autowired
  SnsDao snsDao;

  @RequestMapping("/sns/add")
  public Object add(Sns sns) {
    return snsDao.insert(sns);
  }

  @RequestMapping("/sns/get")
  public Object get(int no) {
    return snsDao.findByNo(no);
  }

  @RequestMapping("/sns/list")
  public Object list() {
    return snsDao.findAll(); 
  }

  @RequestMapping("/sns/update")
  public Object update(Sns sns) {
    return snsDao.update(sns);
  }

  @RequestMapping("/sns/delete")
  public Object delete(int no) {
    return snsDao.delete(no);
  }
}

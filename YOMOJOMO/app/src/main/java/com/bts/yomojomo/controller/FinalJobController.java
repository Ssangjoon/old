package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bts.yomojomo.dao.FinalJobDao;
import com.bts.yomojomo.domain.FinalJob;

@RestController
public class FinalJobController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  FinalJobDao finaljobDao;

  @RequestMapping("/finaljob/add")
  public Object add(FinalJob finaljob) {
    return finaljobDao.insert(finaljob);
  }

  @RequestMapping("/finaljob/get")
  public Object get(int no) {
    return finaljobDao.findByNo(no);
  }

  @RequestMapping("/finaljob/list")
  public Object list() {
    return finaljobDao.findAll(); 
  }

  @RequestMapping("/finaljob/update")
  public Object update(FinalJob finaljob) {
    return finaljobDao.update(finaljob);
  }

  @RequestMapping("/finaljob/delete")
  public Object delete(int no) {
    return finaljobDao.delete(no);
  }
}

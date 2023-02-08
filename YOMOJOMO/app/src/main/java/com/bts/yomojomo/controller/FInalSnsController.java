package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bts.yomojomo.dao.FinalSnsDao;
import com.bts.yomojomo.domain.FinalSns;

@RestController
public class FInalSnsController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  FinalSnsDao finalsnsDao;

  @RequestMapping("/finalsns/add")
  public Object add(FinalSns finalsns) {
    return finalsnsDao.insert(finalsns);
  }

  @RequestMapping("/finalsns/get")
  public Object get(int no) {
    return finalsnsDao.findByNo(no);
  }

  @RequestMapping("/finalsns/list")
  public Object list() {
    return finalsnsDao.findAll(); 
  }

  @RequestMapping("/finalsns/update")
  public Object update(FinalSns finalsns) {
    return finalsnsDao.update(finalsns);
  }

  @RequestMapping("/finalsns/delete")
  public Object delete(int no) {
    return finalsnsDao.delete(no);
  }
}

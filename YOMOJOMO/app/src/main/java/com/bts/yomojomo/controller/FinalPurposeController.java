package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bts.yomojomo.dao.FinalPurposeDao;
import com.bts.yomojomo.domain.FinalPurpose;

@RestController
public class FinalPurposeController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  FinalPurposeDao finalpurposeDao;

  @RequestMapping("/finalpurpose/add")
  public Object add(FinalPurpose finalpurpose) {
    return finalpurposeDao.insert(finalpurpose);
  }

  @RequestMapping("/finalpurpose/get")
  public Object get(int no) {
    return finalpurposeDao.findByNo(no);
  }

  @RequestMapping("/finalpurpose/list")
  public Object list() {
    return finalpurposeDao.findAll(); 
  }

  @RequestMapping("/finalpurpose/update")
  public Object update(FinalPurpose finalpurpose) {
    return finalpurposeDao.update(finalpurpose);
  }

  @RequestMapping("/finalpurpose/delete")
  public Object delete(int no) {
    return finalpurposeDao.delete(no);
  }
}

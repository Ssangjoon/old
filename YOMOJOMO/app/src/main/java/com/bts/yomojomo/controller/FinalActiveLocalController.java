package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bts.yomojomo.dao.FinalActiveLocalDao;
import com.bts.yomojomo.domain.FinalActiveLocal;

@RestController
public class FinalActiveLocalController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  FinalActiveLocalDao finalactivelocalDao;

  @RequestMapping("/finalactivelocal/add")
  public Object add(FinalActiveLocal finalactivelocal) {
    return finalactivelocalDao.insert(finalactivelocal);
  }

  @RequestMapping("/finalactivelocal/get")
  public Object get(int no) {
    return finalactivelocalDao.findByNo(no);
  }

  @RequestMapping("/finalactivelocal/list")
  public Object list() {
    return finalactivelocalDao.findAll(); 
  }

  @RequestMapping("/finalactivelocal/update")
  public Object update(FinalActiveLocal finalactivelocal) {
    return finalactivelocalDao.update(finalactivelocal);
  }

  @RequestMapping("/finalactivelocal/delete")
  public Object delete(int no) {
    return finalactivelocalDao.delete(no);
  }
}

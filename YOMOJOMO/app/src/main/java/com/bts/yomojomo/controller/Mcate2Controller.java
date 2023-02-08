package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.Mcate2Dao;
import com.bts.yomojomo.domain.Mcate2;

@RestController
public class Mcate2Controller {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  Mcate2Dao mcate2Dao;

  @RequestMapping("/mcate2/add")
  public Object add(Mcate2 mcate2) {
    return mcate2Dao.insert(mcate2);
  }

  @RequestMapping("/mcate2/get")
  public Object get(int no) {
    return mcate2Dao.findByNo(no);
  }

  @RequestMapping("/mcate2/list")
  public Object list() {
    return mcate2Dao.findAll(); 
  }

  @RequestMapping("/mcate2/update")
  public Object update(Mcate2 mcate2) {
    return mcate2Dao.update(mcate2);
  }

  @RequestMapping("/mcate2/delete")
  public Object delete(int no) {
    return mcate2Dao.delete(no);
  }
}

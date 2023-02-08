package com.bts.yomojomo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.KeywordDao;
import com.bts.yomojomo.domain.Keyword;



@RestController
public class KeywordController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  KeywordDao keywordDao;

  @RequestMapping("/keyword/add")
  public int add(Keyword keyword) {
    return keywordDao.insert(keyword);
  }

  @RequestMapping("/keyword/get")
  public Object get(int kNo) {
    return keywordDao.findByNo(kNo);
  }

  @RequestMapping("/keyword/list")
  public Object list() {
    return keywordDao.findAll(); 
  }

  @RequestMapping("/keyword/update")
  public int update(Keyword keyword) {
    return keywordDao.update(keyword);
  }

  @RequestMapping("/keyword/delete")
  public Object delete(int kNo) {
    return keywordDao.delete(kNo);
  }
}
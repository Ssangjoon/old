package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.QueryCate;
import com.bts.yomojomo.service.QueryCateService;

@RestController
public class QueryCateController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  QueryCateService queryCateService;

  @RequestMapping("/queryCate/add")
  public Object add(QueryCate querycate) {
    return queryCateService.add(querycate);
  }

  @RequestMapping("/queryCate/get")
  public Object get(int no) {
    return queryCateService.get(no);
  }

  @RequestMapping("/queryCate/list")
  public Object list() {
    return queryCateService.list(); 
  }

  @RequestMapping("/queryCate/update")
  public Object update(QueryCate querycate) {
    return queryCateService.update(querycate);
  }

  @RequestMapping("/queryCate/delete")
  public Object delete(int no) {
    return queryCateService.delete(no);
  }
}

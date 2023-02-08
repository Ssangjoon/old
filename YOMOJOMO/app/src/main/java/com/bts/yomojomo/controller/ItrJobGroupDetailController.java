package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.ItrJobGroupDetailDao;
import com.bts.yomojomo.domain.ItrJobGroupDetail;


@RestController
public class ItrJobGroupDetailController {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  ItrJobGroupDetailDao itrJobGroupDetailDao;

  @RequestMapping("/itrjobgroupdetail/add")
  public int add(ItrJobGroupDetail itrJobGroupDetail) {
    return itrJobGroupDetailDao.insert(itrJobGroupDetail);
  }

  @RequestMapping("/itrjobgroupdetail/get")
  public Object get(int no) {
    return itrJobGroupDetailDao.findByNo(no);
  }

  @RequestMapping("/itrjobgroupdetail/list")
  public Object list() {
    return itrJobGroupDetailDao.findAll(); 
  }

  @RequestMapping("/itrjobgroupdetail/update")
  public int update(ItrJobGroupDetail itrJobGroupDetail) {
    return itrJobGroupDetailDao.update(itrJobGroupDetail);
  }

  @RequestMapping("/itrjobgroupdetail/delete")
  public Object delete(int no) {
    return itrJobGroupDetailDao.delete(no);
  }
}
package com.bts.yomojomo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.service.PurposeService;

@RestController
public class PurposeController {

  private static final Logger log = LoggerFactory.getLogger(ActiveLocalController.class);

  @Autowired
  PurposeService purposeService;

  @RequestMapping("/purpose/list")
  public Object list() {
    log.info("purpose 리스트 출력");
    return purposeService.list();
  }

  @RequestMapping("/purpose/get")
  public Object get (int no) {
    log.info("purpose 번호로 검색");
    return purposeService.get(no);
  }
}

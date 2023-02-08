package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Destination;
import com.bts.yomojomo.service.DestinationService;

@RestController
public class DestinationController {

  @Autowired
  DestinationService destnationService;

  @RequestMapping("/junho/midpoint/add")
  public Object add(Destination destination) {
    System.out.println("test" +destination);
    return destnationService.add(destination);
  }

  @RequestMapping("/junho/midpoint/list")
  public Object list(Destination destination) {
    return destnationService.list();
  }


}

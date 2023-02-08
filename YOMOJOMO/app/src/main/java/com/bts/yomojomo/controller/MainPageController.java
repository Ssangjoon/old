package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.service.MainPageService;

@RestController
public class MainPageController {

  @Autowired
  MainPageService mainPageService;

  @RequestMapping("/mainpage/group/nonmemberlist")
  public Object list() {
    return new ResultMap().setStatus(SUCCESS).setData(mainPageService.nonmemberGroupList());
  }

  @RequestMapping("/mainpage/group/memberlist")
  public Object memberList(String membNo) {
    int memberNumber = Integer.parseInt(membNo);
    return new ResultMap().setStatus(SUCCESS).setData(mainPageService.memberGroupList(memberNumber));
  }

}

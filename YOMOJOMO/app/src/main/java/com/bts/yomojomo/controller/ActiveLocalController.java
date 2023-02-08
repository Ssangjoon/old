package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.ActiveLocal;
import com.bts.yomojomo.service.ActiveLocalService;


//->요청 파라미터 분석 및 가공
//-> 응답 데이터 가공 
@RestController
public class ActiveLocalController {

  private static final Logger log = LoggerFactory.getLogger(ActiveLocalController.class);

  @Autowired
  ActiveLocalService activeLocalService ;

  // 기존코드
  //  @Autowired
  //  ActiveLocalService activeLocalService ;
  //
  @RequestMapping("/activeLocal/list")
  public Object list() {
    return activeLocalService.list(); 
  }
  @RequestMapping("/activeLocal/get")
  public Object get(int no) {
    return activeLocalService.get(no);
  }

  @RequestMapping("/activeLocal/list-gu")
  public Object list(String nameGu) {
    return activeLocalService.list(nameGu);
  }

  //로그인 세션 적용
  @RequestMapping("/activeLocal/silistcate")
  public Object silistcate(HttpSession session) {
    log.info("시,도 리스트 조회");
    return new ResultMap().setStatus(SUCCESS).setData(activeLocalService.silistcate()); 
  }

  @RequestMapping("/activeLocal/gulistcate")
  public Object gulistcate(String nameSi, HttpSession session) {
    log.info("군,구 리스트 조회");

    ActiveLocal activeLocal = new ActiveLocal();
    activeLocal.setNameSi(nameSi);
    return new ResultMap().setStatus(SUCCESS).setData(activeLocalService.gulistcate(activeLocal));
  }

}

package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.ActiveLocalDao;
import com.bts.yomojomo.domain.ActiveLocal;
import com.bts.yomojomo.service.ActiveLocalService;

//서비스객체 -> 업무로직, 트랜잭션 제어 
// => 일관성을 위해 반드시 서비스 객체를 통해 DAO를 사용한다. 
@Service // Spring IoC 컨테이너가 객체를 만들어 저장할 때 클래스 이름을 사용한다. 예) defaultBoardService
public class DefaultActiveLocalService implements ActiveLocalService {

  @Autowired
  ActiveLocalDao activeLocalDao;

  @Override
  public List<ActiveLocal> list() {
    return activeLocalDao.findSi();
  }

  @Override
  public ActiveLocal get(int no) {
    ActiveLocal activeLocal = activeLocalDao.findByNo(no);
    return activeLocal;
  }
  @Override
  public List<ActiveLocal> list(String si) {
    return activeLocalDao.findGungu(si);
  }


  // 새로 작성
  @Override
  public List<ActiveLocal> silistcate() {
    return activeLocalDao.selectSiList();
  }

  @Override
  public List<ActiveLocal> gulistcate(ActiveLocal activeLocal) {
    return activeLocalDao.selectGuList(activeLocal);
  }


}

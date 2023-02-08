package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.ActiveLocal;

public interface ActiveLocalService {

  // 기존 작성 코드
  List<ActiveLocal> list();

  ActiveLocal get(int no);

  List<ActiveLocal> list(String si);

  List<ActiveLocal> silistcate();

  List<ActiveLocal> gulistcate(ActiveLocal activeLocal);

}

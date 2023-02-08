package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.ActiveLocal;

@Mapper 

public interface ActiveLocalDao {

  ActiveLocal findByNo(int no);

  List<ActiveLocal> findSi();

  List<ActiveLocal> findGungu(String si);


  // 새로 작성
  List<ActiveLocal> selectSiList();

  List<ActiveLocal> selectGuList(ActiveLocal activeLocal);

}

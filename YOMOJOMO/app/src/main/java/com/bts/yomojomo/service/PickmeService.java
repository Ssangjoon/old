package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Pickme;

//코드 수정시 저한테 말해주세요 - 경현

public interface PickmeService {

  int add(Pickme pickme);

  List<Pickme> list(int pageSize, int pageNo, String nameSi, String nameGu, String keyword);

  List<Pickme> listbyMembNo(int pageSize, int pageNo, int memberNo);

  Pickme get(int no);

  int update(Pickme pickme);

  int delete(Pickme pickme);

  int size(String nameSi, String nameGu, String keyword);

  int mypageSize(int memberNo);




}

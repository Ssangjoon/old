package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Group;

//코드 수정시 저한테 말해주세요 - 경현

public interface GroupService {

  int add(Group group);

  List<Group> list();

  List<Group> siList(Group group);

  List<Group> guList(Group group);

  List<Group> selectedPurpcate(Group group);

  Group get(int no);

  Group getview(int no);

  int update(Group group);

  int delete(int no);

  int updateStatus(int no, int status);

  List<Group> listselect(int no, int cutno, String searchKeyword);

  int countSelect(String searchKeyword);
}

package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.MainPage;

public interface MainPageService {
  List<MainPage> nonmemberGroupList();
  List<MainPage> memberGroupList(int no);
}

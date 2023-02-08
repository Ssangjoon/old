package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.MainPage;

@Mapper
public interface MainPageDao {
  List<MainPage> nonMemberGroupList();

  List<MainPage> memberGroupList(int no);
}

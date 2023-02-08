package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.MemberMidpointSelect;

@Mapper
public interface MemberMidpointSelectDao {
  List<MemberMidpointSelect> findAll(MemberMidpointSelect memberMidpointSelect);

  int insert(MemberMidpointSelect memberMidpointSelect);

  int delete(MemberMidpointSelect memberMidpointSelect);
}

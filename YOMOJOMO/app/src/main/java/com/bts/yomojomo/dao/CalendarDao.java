package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.Calendar;

@Mapper 
public interface CalendarDao {
  int countAll();

  List<Calendar> findAll();

  List<Calendar> findListByGroup(Calendar calendar);

  int insert(Calendar calendar);

  Calendar findByNo(int no);

  int update(Calendar calendar);

  int delete(Calendar calendar);
}

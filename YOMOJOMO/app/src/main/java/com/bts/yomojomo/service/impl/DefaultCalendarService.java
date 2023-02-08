package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.CalendarDao;
import com.bts.yomojomo.domain.Calendar;
import com.bts.yomojomo.service.CalendarSerivce;

@Service 
public class DefaultCalendarService implements CalendarSerivce{

  @Autowired
  CalendarDao calendarDao;

  @Transactional
  @Override
  public int add(Calendar calendar) {
    return calendarDao.insert(calendar);
  }

  @Override
  public List<Calendar> list() {
    return calendarDao.findAll(); 
  }

  @Override
  public List<Calendar> listByGroup(Calendar calendar) {
    return calendarDao.findListByGroup(calendar);
  }

  @Override
  public Calendar get(int no) {
    return calendarDao.findByNo(no);
  }

  @Transactional
  @Override
  public int update(Calendar calendar) {
    return calendarDao.update(calendar);
  }

  @Transactional
  @Override
  public int delete(Calendar calendar) {
    return calendarDao.delete(calendar);
  }



}

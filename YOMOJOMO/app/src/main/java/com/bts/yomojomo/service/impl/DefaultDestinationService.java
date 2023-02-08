package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.DestinationDao;
import com.bts.yomojomo.domain.Destination;
import com.bts.yomojomo.service.DestinationService;

@Service
public class DefaultDestinationService implements DestinationService {

  @Autowired
  DestinationDao destinationDao;

  @Override
  public List<Destination> list() {
    return destinationDao.findAll();
  }

  @Override
  public int add(Destination destination) {
    return destinationDao.insert(destination);
  }

  @Override
  public Destination get(int no) {
    return destinationDao.findByNo(no);
  }

  @Override
  public int update(Destination destination) {
    return destinationDao.update(destination);
  }

  @Override
  public int delete(Destination destination) {
    return destinationDao.delete(destination.getNo());
  }

}

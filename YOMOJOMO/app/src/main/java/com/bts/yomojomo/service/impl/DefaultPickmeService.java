package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.PickmeDao;
import com.bts.yomojomo.domain.Pickme;
import com.bts.yomojomo.service.PickmeService;

//코드 수정시 저한테 말해주세요 - 경현

@Service
public class DefaultPickmeService implements PickmeService{

  @Autowired
  PickmeDao pickmeDao;

  @Transactional
  @Override
  public int add(Pickme pickme) {
    return pickmeDao.insert(pickme);
  }

  @Override
  public List<Pickme> list(int pageSize, int pageNo, String nameSi, String nameGu, String keyword) {
    return pickmeDao.findAll(pageSize, ((pageNo - 1) * pageSize), nameSi, nameGu, keyword);
  }

  @Override
  public Pickme get(int no) {
    Pickme pickme = pickmeDao.findByNo(no);

    if (pickme != null) {
      pickmeDao.increaseViewCount(no);
    }
    return pickme;
  }

  @Transactional
  @Override
  public int update(Pickme pickme) {
    return pickmeDao.update(pickme);
  }

  @Transactional
  @Override
  public int delete(Pickme pickme) {
    return pickmeDao.delete(pickme);
  }

  @Override
  public int size(String nameSi, String nameGu, String keyword) {
    return pickmeDao.countAll(nameSi, nameGu, keyword);
  }

  @Override
  public int mypageSize(int memberNo) {
    return pickmeDao.countMebAll(memberNo);
  }

  @Override
  public List<Pickme> listbyMembNo(int pageSize, int pageNo, int memberNo) {
    return pickmeDao.findListBymembNo(pageSize, ((pageNo - 1) * pageSize), memberNo);
  }



}

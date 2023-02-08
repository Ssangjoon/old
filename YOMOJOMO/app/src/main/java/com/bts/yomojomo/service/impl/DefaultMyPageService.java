package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.MyPageDao;
import com.bts.yomojomo.domain.MyPage;
import com.bts.yomojomo.service.MyPageService;

@Service
public class DefaultMyPageService implements MyPageService {

  @Autowired
  MyPageDao mypageDao;

  @Override
  public List<MyPage> list() {
    return mypageDao.findAll();
  }

  @Override
  public MyPage get(int no) {
    MyPage mypage = mypageDao.findByMemberNo(no);
    return mypage;
  }

  @Override
  public int update(MyPage mypage) {
    mypageDao.update(mypage);
    return 1;
  }

  @Override
  public int deleteCategory(MyPage mypage) {
    mypageDao.deleteLocal(mypage);
    mypageDao.deletePurpose(mypage);
    return 1;
  }

  @Override
  @Transactional
  public int insertCategory(MyPage mypage) {
    mypageDao.insertLocal(mypage.getNo(), mypage.getFinalActiveLocal());
    mypageDao.insertPurpose(mypage.getNo(), mypage.getFinalPurpose());
    return 1;
  }

  @Override
  public MyPage myPost(int no) {
    return mypageDao.findByMyPost(no);
  }

  @Override
  public MyPage myScrap(int no) {
    MyPage mypage = mypageDao.findByMyScrap(no);
    return mypage;
  }

  @Override
  public MyPage myNotice(int no) {
    return mypageDao.findByMyNotice(no);
  }
}

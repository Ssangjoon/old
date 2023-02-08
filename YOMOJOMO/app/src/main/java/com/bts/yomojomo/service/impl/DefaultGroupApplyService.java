package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.GroupApplyFormDao;
import com.bts.yomojomo.domain.GroupApplyForm;
import com.bts.yomojomo.service.GroupApplyService;

@Service
public class DefaultGroupApplyService implements GroupApplyService{

  @Autowired
  GroupApplyFormDao groupApplyFormDao;

  @Override
  public List<GroupApplyForm> sendList(GroupApplyForm groupApplyForm) {
    return groupApplyFormDao.sendListFindALL(groupApplyForm);
  }

  @Override
  public int sendDelete(GroupApplyForm groupApplyForm) {
    return groupApplyFormDao.sendListDelete(groupApplyForm);
  }

  @Override
  public List<GroupApplyForm> reciveList(GroupApplyForm groupApplyForm) {
    return groupApplyFormDao.reciveListFindAll(groupApplyForm);
  }

  @Override
  public int add(GroupApplyForm groupApplyForm) {
    groupApplyFormDao.membCnt(groupApplyForm.getGroup().getNo());
    return groupApplyFormDao.insert(groupApplyForm);
  }

  @Override
  public int reciveDelete(GroupApplyForm groupApplyForm) {
    return groupApplyFormDao.reciveListDelete(groupApplyForm);
  }

}

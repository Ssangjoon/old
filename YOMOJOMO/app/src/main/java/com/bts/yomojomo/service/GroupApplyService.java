package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.GroupApplyForm;

public interface GroupApplyService {

  List<GroupApplyForm> sendList(GroupApplyForm groupApplyForm);

  int sendDelete(GroupApplyForm groupApplyForm);

  List<GroupApplyForm> reciveList(GroupApplyForm groupApplyForm);

  int add(GroupApplyForm groupApplyForm);

  int reciveDelete(GroupApplyForm groupApplyForm);
}

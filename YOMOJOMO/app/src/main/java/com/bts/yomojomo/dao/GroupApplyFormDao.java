package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.GroupApplyForm;

@Mapper
public interface GroupApplyFormDao {

  List<GroupApplyForm> sendListFindALL(GroupApplyForm groupApplyForm);

  int sendListDelete(GroupApplyForm groupApplyForm);

  List<GroupApplyForm> reciveListFindAll(GroupApplyForm groupApplyForm);

  int insert(GroupApplyForm groupApplyForm);

  int membCnt(int no);

  int reciveListDelete(GroupApplyForm groupApplyForm);
}

package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.AccountingStatus;

//적절한 키가 없어서 파라미터 모금번호로 설정함

@Mapper
public interface AccountingStatusDao {
  int countAll();

  List<AccountingStatus> findAll();

  int insert(AccountingStatus accountingStatus);

  AccountingStatus findByNo(int actno);

  int update(AccountingStatus accountingStatus);

  int delete(int actno);
}

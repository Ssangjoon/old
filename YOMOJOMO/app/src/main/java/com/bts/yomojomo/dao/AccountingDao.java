package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.Accounting;
import com.bts.yomojomo.domain.AccountingCate;
import com.bts.yomojomo.domain.AccountingStatus;

@Mapper 
public interface AccountingDao {
  int countAll(
      @Param("groupNo") int groupNo, 
      @Param("actCate") String actCate
      );

  List<Accounting> findListByGroup(
      @Param("rowCount") int rowCount, 
      @Param("offset") int offset, 
      @Param("groupNo") int groupNo, 
      @Param("actCate") String actCate
      );

  List<AccountingCate> cateListAll();

  int insert(Accounting accounting);

  int insertActStatus(
      @Param("accountingNo") int accountingNo,
      @Param("actstatuses") List<AccountingStatus> actStatus,
      @Param("qslength") int qslength
      );

  Accounting findByNo(int no);

  int update(Accounting accounting);

  int delete(int accountingNo);

  List<Accounting> findAll();

  List<Accounting> findListByGroup(@Param ("groupNo") int groupNo);

  int deleteStatusByNo(int accountingNo);



}

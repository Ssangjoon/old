package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.FinalActiveLocal;
import com.bts.yomojomo.domain.FinalPurpose;
import com.bts.yomojomo.domain.MyPage;

@Mapper
public interface MyPageDao {

  List<MyPage> findAll();

  MyPage findByMemberNo(int no);

  int update(MyPage mypage);

  int deleteLocal(MyPage mypage);

  int deletePurpose(MyPage mypage);

  int insertLocal(@Param("no") int no, @Param("locals") List<FinalActiveLocal> finalActiveLocal);

  int insertPurpose(@Param("no") int no, @Param("pups") List<FinalPurpose> finalPurpose);

  MyPage findByMyPost(int no);

  MyPage findByMyScrap(int no);

  MyPage findByMyNotice(int no);
}

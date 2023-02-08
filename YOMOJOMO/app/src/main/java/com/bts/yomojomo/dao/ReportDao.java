package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.Report;

@Mapper
//=> Mybatis에서 DAO 구현체를 자동으로 생성한다.
//=> DAO 구현체가 사용할 SQL Mapper 파일의 위치는 인터페이스의 패키지 경로 및 이름과 일치해야 한다.
//=> 인터페이스의 메서드가 호출될 때 사용할 SQL ID는 메서드 이름과 일치해야 한다.
public interface ReportDao {


  List<Report> getBoardListSelect(@Param("no")int no,@Param("cutno") int cutno, @Param("searchKeyword") String searchKeyword);

  int getBoardListSelectCount(String searchKeyword);

  int valid(@Param("no") int no, @Param("valid") int valid);

  int reportDelete(@Param("no") int no);

  Report findByNo(int no);

  int insert(Report report);

}

package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.FinalActiveLocal;
import com.bts.yomojomo.domain.FinalPurpose;
import com.bts.yomojomo.domain.Member;

@Mapper 
//=> Mybatis에서 DAO 구현체를 자동으로 생성한다.
//=> DAO 구현체가 사용할 SQL Mapper 파일의 위치는 인터페이스의 패키지 경로 및 이름과 일치해야 한다.
//=> 인터페이스의 메서드가 호출될 때 사용할 SQL ID는 메서드 이름과 일치해야 한다.
public interface MemberDao {
  //  int countAll();
  //
  List<Member> findAll();
  //
  //  int insert(Member member);
  //
  //  Member findByNo(int no);
  //
  //  int update(Member member);
  //
  //  int delete(int no);
  //  
  int insert(Member member);

  int insertLocals(@Param("no") int no, @Param("locals") List<FinalActiveLocal> locals);

  int insertPups(@Param("no") int no, @Param("pups") List<FinalPurpose> pups);

  List<Member> getBoardListSelect(@Param("no") int no, @Param("cutno") int cutno,
      @Param("searchKeyword") String searchKeyword);

  int getBoardListSelectCount(String searchKeyword);

  int penalty(@Param("no") int no, @Param("status") int status);

  int membDelete(@Param("no") int no);

  Member findByNo(int no);
  
  Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password, @Param("level") String level);

  Member findByEmail(String email);

  Member findidByNameandTel(@Param("name") String name, @Param("tel") String tel);

  Member findpwdByEmailandTel(@Param("email") String email, @Param("tel") String tel);

  Member checkEmail(String email);

  void updatepwd(@Param("password") String password, @Param("email") String email);

}

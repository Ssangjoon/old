package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.Pickme;

@Mapper 
public interface PickmeDao {
  int countAll(
      @Param("nameSi") String nameSi, 
      @Param("nameGu") String nameGu,
      @Param("keyword") String keyword
      );

  int countMebAll (@Param("memberNo") int memberNo);

  int countSiList(@Param("nameSi") String nameSi);

  List<Pickme> findAll(
      @Param("rowCount") int rowCount, 
      @Param("offset") int offset, 
      @Param("nameSi") String nameSi, 
      @Param("nameGu") String nameGu,
      @Param("keyword") String keyword
      );

  List<Pickme> findListBymembNo (
      @Param("rowCount") int rowCount, 
      @Param("offset") int offset, 
      @Param("memberNo") int memberNo);

  Pickme findByNo(int no);

  Pickme findByName(int memberName);

  int insert(Pickme pickme);

  int update(Pickme pickme);

  int delete(Pickme pickme);

  int increaseViewCount(int no);


}

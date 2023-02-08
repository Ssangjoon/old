package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.Sns;

@Mapper
public interface SnsDao {

  List<Sns> findAll();

  int insert(Sns sns);

  Sns findByNo(int no);

  int update(Sns sns);

  int delete(int no);
}

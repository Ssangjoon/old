package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.Destination;

@Mapper
public interface DestinationDao {
  int countAll();

  List<Destination> findAll();

  int insert(Destination destination);

  Destination findByNo(int no);

  int update(Destination destination);

  int delete(int no);
}

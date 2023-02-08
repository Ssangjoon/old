package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.Purpose;

@Mapper 
public interface PurposeDao {
  List<Purpose> findAll();

  Purpose findByNo(int no);

}

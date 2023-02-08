package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.QueryCate;
import com.bts.yomojomo.domain.Sns;

@Mapper
public interface QueryCateDao {

  List<QueryCate> findAll();

  int insert(QueryCate querycate);

  QueryCate findByNo(int no);

  int update(QueryCate querycate);

  int delete(int no);
}

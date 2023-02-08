package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.QueryCateDao;
import com.bts.yomojomo.domain.QueryCate;
import com.bts.yomojomo.service.QueryCateService;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class DefaultQueryCateService implements QueryCateService {
  
  @Autowired
  QueryCateDao queryCateDao;
  
  @Override
  public int add(QueryCate querycate) {
	  return queryCateDao.insert(querycate);
	}

  @Override
  public List<QueryCate> list() {
    return queryCateDao.findAll();
  }

  @Override
  public QueryCate get(int no) {
    return queryCateDao.findByNo(no);
  }

  @Override
  public int update(QueryCate querycate) {
    return queryCateDao.update(querycate);
  }

  @Override
  public int delete(int no) {
    return queryCateDao.delete(no);
  }

}

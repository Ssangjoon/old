package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.QueryCate;

public interface QueryCateService {

	int add(QueryCate querycate);

	List<QueryCate> list();

	QueryCate get(int no);

	int update(QueryCate querycate);

	int delete(int no);

}

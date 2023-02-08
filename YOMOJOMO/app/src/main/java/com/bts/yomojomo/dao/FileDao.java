package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.File;

@Mapper 
public interface FileDao {
  int countAll();

  List<File> findAll();

  int insert(File file);

  File findByNo(int no);

  int update(File file);

  int delete(int no);
}

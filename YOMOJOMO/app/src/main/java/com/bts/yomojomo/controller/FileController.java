package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.FileDao;
import com.bts.yomojomo.domain.File;

@RestController
public class FileController {
  @Autowired
  FileDao fileDao;

  @RequestMapping("/file/add")
  public Object add(File file) {
    return fileDao.insert(file);
  }

  @RequestMapping("/file/get")
  public Object get(int no) {
    return fileDao.findByNo(no);
  }

  @RequestMapping("/file/list")
  public Object list() {
    return fileDao.findAll(); 
  }

  @RequestMapping("/file/update")
  public Object update(File file) {
    return fileDao.update(file);
  }

  @RequestMapping("/file/delete")
  public Object delete(int no) {
    return fileDao.delete(no);
  }
}

package com.bts.yomojomo.domain;

public class Photo {
  int no;
  int bNo;
  String path;

  @Override
  public String toString() {
    return "Photo [no=" + no + ", bNo=" + bNo + ", path=" + path + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getbNo() {
    return bNo;
  }
  public void setbNo(int bNo) {
    this.bNo = bNo;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }

}

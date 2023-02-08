package com.bts.yomojomo.domain;

public class ItrJobGroupDetail {
  int no;
  String name;
  int itrjno;
  
  @Override
  public String toString() {
    return "ItrJobGroupDetail [no=" + no + ", name=" + name + ", itrjno=" + itrjno + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getItrjno() {
    return itrjno;
  }
  public void setItrjno(int itrjno) {
    this.itrjno = itrjno;
  }
  

  

}

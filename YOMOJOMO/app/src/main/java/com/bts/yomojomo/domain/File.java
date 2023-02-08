package com.bts.yomojomo.domain;

public class File {
  int no;
  int bno;
  String name;

  @Override
  public String toString() {
    return "File [no=" + no + ", bno=" + bno + ", name=" + name + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getBno() {
    return bno;
  }

  public void setBno(int bno) {
    this.bno = bno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

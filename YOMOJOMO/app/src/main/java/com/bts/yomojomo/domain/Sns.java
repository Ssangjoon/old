package com.bts.yomojomo.domain;

public class Sns {
  int no;
  String name;

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
  @Override
  public String toString() {
    return "Sns [no=" + no + ", name=" + name + "]";
  }
}

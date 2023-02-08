package com.bts.yomojomo.domain;

public class Keyword {
  int kNo;
  String searchName;
  int membNo;
  
  
  @Override
  public String toString() {
    return "Keyword [kNo=" + kNo + ", searchName=" + searchName + ", membNo=" + membNo + "]";
  }
  public int getKno() {
    return kNo;
  }
  public void setKno(int kNo) {
    this.kNo = kNo;
  }
  public String getSearchName() {
    return searchName;
  }
  public void setSearchName(String searchName) {
    this.searchName = searchName;
  }
  public int getMembNo() {
    return membNo;
  }
  public void setMembNo(int membNo) {
    this.membNo = membNo;
  }
  

  
  

}

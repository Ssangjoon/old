package com.bts.yomojomo.domain;

import lombok.Data;

@Data
public class GroupTag {
  public GroupTag(String tagname) {
    this.tagname = tagname;
  }
  int no;
  int gno;
  String tagname;
}

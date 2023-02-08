package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class GroupApplyForm {
  String content;
  Date regdt;
  Group group;
  Member sendMember;
  Member reciveMember;
}

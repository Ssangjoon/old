package com.bts.yomojomo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JoinMember {
  Group group;
  Member member;
  MembGrade memberGrade;
}

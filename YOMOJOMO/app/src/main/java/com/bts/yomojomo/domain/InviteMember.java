package com.bts.yomojomo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InviteMember {
  Group group;
  Member member;
}

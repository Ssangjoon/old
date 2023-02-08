package com.bts.yomojomo.domain;

import lombok.Data;

@Data
public class MemberMidpointSelect {
  int no; // gms_select_midpoint memb_pt_no
  String lat; // gms_select_midpoint lat
  String lng; // gms_select_midpoint lng
  String addr; // gms_select_midpoint addr

  Member member; // gms_select_midpoint memb_no
  Group group; // gms_select_midpoint g_no
  Calendar calendar; // gms_select_midpoint cal_no
}

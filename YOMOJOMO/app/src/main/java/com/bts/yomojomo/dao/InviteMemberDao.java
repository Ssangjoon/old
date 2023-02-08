package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.InviteMember;

@Mapper
public interface InviteMemberDao {
  List<InviteMember> findAll(InviteMember invitemember);
}

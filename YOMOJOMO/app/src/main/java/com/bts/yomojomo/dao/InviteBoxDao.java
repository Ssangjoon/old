package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bts.yomojomo.domain.InviteBox;

@Mapper 
public interface InviteBoxDao {

  List<InviteBox> findInviteListByRecipient(InviteBox inviteBox);

  List<InviteBox> findInviteListByRecipientPaging(
      @Param("rowCount") int rowCount, 
      @Param("offset") int offset, 
      @Param("memberNo") int memberNo);

  List<InviteBox> findinvitelistBySender(
      @Param("rowCount") int rowCount, 
      @Param("offset") int offset, 
      @Param("memberNo") int memberNo);

  int countSender(@Param("memberNo") int memberNo);

  int countRecipient(@Param("memberNo") int memberNo);

  int createInvitation(InviteBox inviteBox);

  int delete(InviteBox inviteBox);

  InviteBox findByNo(
      @Param("inviteNo") int inviteNo, 
      @Param("senderNo") int senderNo);

  InviteBox findInviteByRecivedNo(@Param("inviteNo") int inviteNo, @Param("recivedNo") int memberNo);

  int update(InviteBox inviteBox);

  int confirmUpdate(InviteBox inviteBox);

}

package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.InviteBox;

public interface InviteBoxService {

  List<InviteBox> inviteListByRecipient(InviteBox inviteBox);

  List<InviteBox> inviteListBySenderPaging(int pageSize, int pageNo, int memberNo);

  List<InviteBox> inviteListBySender(int pageSize, int pageNo, int memberNo);

  int mypageSize(int memberNo);

  int mypageRecipientSize(int memberNo);

  int send(InviteBox inviteBox);

  InviteBox get(int inviteNo, int senderNo);

  InviteBox recivedInvite(int inviteNo, int memberNo);

  int delete(InviteBox inviteBox);

  int update(InviteBox inviteBox);

  int confirmUpdate(InviteBox inviteBox);

}

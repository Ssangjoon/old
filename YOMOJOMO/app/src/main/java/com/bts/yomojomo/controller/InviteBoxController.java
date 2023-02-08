package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.InviteBox;
import com.bts.yomojomo.domain.JoinMember;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.service.InviteBoxService;

@RestController
public class InviteBoxController {
  private static final Logger log = LoggerFactory.getLogger(InviteBoxController.class);

  @Autowired
  InviteBoxService inviteBoxService;

  @RequestMapping("/invitebox/invitelistbyrecipient")
  public Object invitelistByrecipient(InviteBox inviteBox, HttpSession session) {
    log.info("초대받은 회원별 받은 초대 리스트 조회");

    return new ResultMap().setStatus(SUCCESS).setData(inviteBoxService.inviteListByRecipient(inviteBox));
  }

  @RequestMapping("/invitebox/invitelistbyrecipientpaing")
  public Object invitelistbyrecipientpaing(int pageSize, int pageNo, int memberNo, HttpSession session) {
    log.info("페이징 적용한 회원별 보낸 초대 리스트 조회");

    Member member = (Member) session.getAttribute("loginUser");
    InviteBox inviteBox = new InviteBox();
    inviteBox.setMember(member);

    try {
      if (pageSize < 3 || pageSize > 100) {
        pageSize = 3;
      }
    } catch (Exception e) {}

    int recipientListSize = inviteBoxService.mypageRecipientSize(memberNo);

    int totalPageSize = recipientListSize / pageSize; 

    if ((recipientListSize % pageSize) > 0) {
      totalPageSize++;
    }

    try { 
      if (pageNo < 1 || pageNo > totalPageSize) {
        pageNo = 1;
      }
    } catch (Exception e) {}

    return new ResultMap()
        .setStatus(SUCCESS)
        .setTotalListCount(recipientListSize)
        .setPageNo(pageNo)
        .setTotalPageSize(totalPageSize)
        .setData(inviteBoxService.inviteListBySenderPaging(pageSize, pageNo, memberNo));
  }

  @RequestMapping("/invitebox/invitelistbysender")
  public Object invitelistBysender(int pageSize, int pageNo, int memberNo, HttpSession session) {
    log.info("초대보낸 회원별 보낸 초대 리스트 조회");
    try {
      if (pageSize < 3 || pageSize > 100) {
        pageSize = 3;
      }
    } catch (Exception e) {}

    int senderListSize = inviteBoxService.mypageSize(memberNo);

    int totalPageSize = senderListSize / pageSize; 

    if ((senderListSize % pageSize) > 0) {
      totalPageSize++;
    }

    try { 
      if (pageNo < 1 || pageNo > totalPageSize) {
        pageNo = 1;
      }
    } catch (Exception e) {}

    return new ResultMap()
        .setStatus(SUCCESS)
        .setTotalListCount(senderListSize)
        .setPageNo(pageNo)
        .setTotalPageSize(totalPageSize)
        .setData(inviteBoxService.inviteListBySender(pageSize, pageNo, memberNo));
  }

  @RequestMapping("/invitebox/delete")
  public Object delete(int no, HttpSession session) {
    log.info("초대메시지 삭제");
    Member member = (Member) session.getAttribute("loginUser");
    InviteBox invitebox = new InviteBox();
    invitebox.setInviteNo(no);
    invitebox.setMember(member);

    int count = inviteBoxService.delete(invitebox);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/invitebox/send")
  public Object sendInvitation(InviteBox inviteBox, HttpSession session) {
    log.info("초대장 발송");
    log.debug(inviteBox.toString());

    Member member = (Member) session.getAttribute("loginUser");
    JoinMember joinMember = new JoinMember();

    joinMember.setMember(member);
    inviteBoxService.send(inviteBox);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/invitebox/get")
  public Object get(int inviteNo, int senderNo, HttpSession session) {
    InviteBox invitebox = inviteBoxService.get(inviteNo, senderNo);

    if (invitebox == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 게시글이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(invitebox);
  }

  @RequestMapping("/invitebox/recivedinvite")
  public Object recivedInvite(int inviteNo, HttpSession session) {

    Member member = (Member) session.getAttribute("loginUser");
    InviteBox invitebox = new InviteBox();
    invitebox.setMember(member);
    int memberNo = invitebox.getMember().getNo();

    invitebox = inviteBoxService.recivedInvite(inviteNo, memberNo);

    if (invitebox == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 게시글이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(invitebox);
  }

  @RequestMapping("/invitebox/update")
  public Object update(InviteBox invitebox, HttpSession session) {
    //    System.out.println(invitebox.toString());
    Member member = (Member) session.getAttribute("loginUser"); 

    JoinMember joinMember = new JoinMember();
    joinMember.setGroup(invitebox.getJoinMember().getGroup());
    joinMember.setMember(member);

    invitebox.setJoinMember(joinMember);
    //    System.out.println(invitebox.toString());

    int count = inviteBoxService.update(invitebox);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/invitebox/confirmupdate") //메세지 읽음
  public Object confirmupdate(InviteBox invitebox, HttpSession session) {
    System.out.println(invitebox.toString());

    Member member = (Member) session.getAttribute("loginUser"); 
    invitebox.setMember(member);

    int count = inviteBoxService.confirmUpdate(invitebox);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

}

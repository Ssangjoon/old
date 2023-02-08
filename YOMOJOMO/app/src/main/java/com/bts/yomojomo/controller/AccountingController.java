package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Accounting;
import com.bts.yomojomo.domain.AccountingStatus;
import com.bts.yomojomo.domain.Group;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.service.AccountingService;

@RestController
public class AccountingController {

  private static final Logger log = LoggerFactory.getLogger(Accounting.class);

  @Autowired 
  AccountingService accountingService;

  @RequestMapping("/accounting/list")
  public Object list() {
    return accountingService.list();
  }

  @RequestMapping("/accounting/listbygroup") //소모임 번호로 리스트 받기
  public Object listByGroup(int pageSize, int pageNo, int groupNo, String actCate, HttpSession session) {
    log.info("모임별 회비정산 목록 조회");

    Member member = (Member) session.getAttribute("loginUser");
    Accounting accounting = new Accounting();
    accounting.setMember(member);

    Group group = new Group();
    accounting.setGroup(group.setNo(groupNo));

    try {
      if (pageSize < 10 || pageSize > 100) {
        pageSize = 10;
      }
    } catch (Exception e) {}

    int accountingSize = accountingService.size(groupNo, actCate);
    System.out.println("게시글 개수 count :  "+ accountingSize);

    int totalPageSize = accountingSize / pageSize;

    try {
      if ((accountingSize % pageSize) > 0) {
        totalPageSize++;
      }
    } catch (Exception e) {}

    try { 
      if (pageNo < 1 || pageNo > totalPageSize) {
        pageNo = 1;
      }
    } catch (Exception e) {}
    System.out.println("totalpageSize 는 " +totalPageSize );
    System.out.println(pageNo);

    return new ResultMap()
        .setStatus(SUCCESS)
        .setTotalListCount(accountingSize)
        .setPageNo(pageNo)
        .setTotalPageSize(totalPageSize)
        .setData(accountingService.listByGroup(pageSize, pageNo, groupNo, actCate));
  }

  @RequestMapping("/accounting/catelist") 
  public Object catelist(HttpSession session) {
    log.info("카테고리 리스트 조회");
    return new ResultMap().setStatus(SUCCESS).setData(accountingService.findCateList());
  }

  @RequestMapping("/accounting/add")
  public Object add(Accounting accounting, int qslength, String[] actStatuses, HttpSession session) {

    for (int i = 0; i < actStatuses.length; i++) {
      System.out.println(actStatuses[i]);
    }

    System.out.println();

    log.info("정산등록");
    log.debug(accounting.toString());

    Member member = (Member) session.getAttribute("loginUser");
    accounting.setMember(member);

    System.out.println("actStatuses.length : " + actStatuses.length);

    ArrayList<AccountingStatus> statusList = new ArrayList<>();

    for (int i = 0; i < actStatuses.length; i++) {

      //      System.out.println("1. for문 안 actStatuses :" + actStatuses[i]);

      String[] value = actStatuses[i].split("_");

      //      System.out.println("value의 길이 : " + value.length);

      //      for (int j = 0; j < value.length; j++) {
      //        System.out.printf("2. for문 안 value :[%s], %s\n", j, value[j]);
      //      }

      if (value.length == 2) {
        if(value[1].length() == 0) {
          continue;
        }
        System.out.println("length가 2얌");

        AccountingStatus accountingStatus = new AccountingStatus(
            Integer.parseInt(value[0]), //그룹번호
            Integer.parseInt(value[1]) //멤버번호
            );
        System.out.println("3. 만들어진 accountingStatus : " + accountingStatus);

        statusList.add(accountingStatus);

        System.out.println("4. statusList : " +statusList );

      } else {
        if(value[1].length() == 0) {
          continue;
        }
        System.out.println("length가 2가 아니야");
        AccountingStatus accountingStatus = new AccountingStatus(
            Integer.parseInt(value[0]), //그룹번호
            Integer.parseInt(value[1]), //멤버번호
            java.sql.Date.valueOf(value[2]) //돈낸날짜
            );
        System.out.println("3. 만들어진 accountingStatus : " + accountingStatus);

        statusList.add(accountingStatus);

        System.out.println("4. statusList : " +statusList );
      }
    }
    accounting.setActStatus(statusList);
    System.out.println("accounting : " + accounting);
    accountingService.add(accounting, qslength);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/accounting/get")
  public Object get(int no, HttpSession session) {
    Accounting accounting = accountingService.get(no);
    if (accounting == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 게시글이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(accounting);
  }

  @RequestMapping("/accounting/update")
  public Object update(Accounting accounting, int qslength, String[] actStatuses, HttpSession session) {
    log.info("정산 수정");
    log.debug(accounting.toString());

    for (int i = 0; i < actStatuses.length; i++) {
      System.out.println("1. for문 안 actStatuses :" + actStatuses[i]);
      System.out.println(actStatuses[i]);
    }

    Member member = (Member) session.getAttribute("loginUser");
    accounting.setMember(member);
    System.out.println("actStatuses.length : " + actStatuses.length);

    ArrayList<AccountingStatus> statusList = new ArrayList<>();
    for (int i = 0; i < actStatuses.length; i++) {
      String[] value = actStatuses[i].split("_");

      System.out.println("value의 길이 : " + value.length);

      for (int j = 0; j < value.length; j++) {
        System.out.printf("2. for문 안 value :[%s], %s\n", j, value[j]);
      }

      if (value.length == 2) {
        if(value[1].length() == 0) {
          continue;
        }
        System.out.println("length가 2얌");
        AccountingStatus accountingStatus = new AccountingStatus(
            Integer.parseInt(value[0]), //그룹번호
            Integer.parseInt(value[1]) //멤버번호
            );
        System.out.println("3. 만들어진 accountingStatus : " + accountingStatus);
        statusList.add(accountingStatus);
        System.out.println("4. statusList : " +statusList );
      } else {

        if(value[1].length() == 0) {
          continue;
        }

        System.out.println("length가 2가 아니야");

        AccountingStatus accountingStatus = new AccountingStatus(
            Integer.parseInt(value[0]), //그룹번호
            Integer.parseInt(value[1]), //멤버번호
            java.sql.Date.valueOf(value[2]) //돈낸날짜
            );
        System.out.println("3. 만들어진 accountingStatus : " + accountingStatus);
        statusList.add(accountingStatus);
        System.out.println("4. statusList : " +statusList );
      }
    }

    accounting.setActStatus(statusList);
    System.out.println("accounting : " + accounting);
    int count = accountingService.update(accounting, qslength);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/accounting/delete")
  public Object delete(int accountingNo, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    Accounting accounting = new Accounting();
    accounting.setAccountingNo(accountingNo);
    accounting.setMember(member);

    int count = accountingService.delete(accountingNo);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }
}
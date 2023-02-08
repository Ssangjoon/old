package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bts.yomojomo.domain.FinalActiveLocal;
import com.bts.yomojomo.domain.FinalPurpose;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.dto.MemberDto;
import com.bts.yomojomo.service.MemberService;

@RestController
public class MemberController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/membercountselect")
  public int getBoardListSelectCount(String searchKeyword) {
    return memberService.countSelect(searchKeyword);
  }

  @RequestMapping("/member/listselect")
  public Object listselect(int no, int cutno, String searchKeyword) {
    int mNo = (no - 1) * cutno; // 넘어온 값에 -1로 데이터베이스에 맞춘다
    return memberService.listselect(mNo, cutno, searchKeyword);
  }
  
  @RequestMapping("/member/get")
  public Member get(int no) {
    return memberService.get(no);
  }

  @RequestMapping("/member/update")
  public Object update(int no, int status) {

    memberService.update(no, status);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/member/delete")
  public Object delete(int no) {

    memberService.delete(no);

    return new ResultMap().setStatus(SUCCESS);
  }
  
  @RequestMapping("/member/signup")
  public Object signUp(Member member, String[] localsArr, String[] pupsArr) throws Exception {

    ArrayList<FinalActiveLocal> locallist = new ArrayList<>();
    for (int i = 0; i < localsArr.length; i++) {
      String value = localsArr[i];
      FinalActiveLocal finalactivelocal = new FinalActiveLocal(Integer.parseInt(value));
      locallist.add(finalactivelocal);

    }
    ArrayList<FinalPurpose> pupslist = new ArrayList<>();
    for (int i = 0; i < pupsArr.length; i++) {
      String value = pupsArr[i];
      FinalPurpose finalpurpose = new FinalPurpose(Integer.parseInt(value));
      pupslist.add(finalpurpose);
    }
    member.setLocals(locallist);
    member.setPups(pupslist);
    memberService.add(member);

    return new ResultMap().setStatus(SUCCESS);

  }

  @RequestMapping("/member/signin")
  public Object signin(String email, String password, String level, boolean saveEmail, HttpServletResponse response,
      HttpSession session) {
    Member loginUser = memberService.get(email, password, level);
    System.out.println(loginUser);
    if (loginUser == null) {

      return new ResultMap().setStatus(FAIL);
    }

    // 로그인이 성공하면,
    // 다른 요청을 처리할 때 로그인 회원의 정보를 사용할 있도록 세션에 보관한다.
    session.setAttribute("loginUser", loginUser);
    Cookie cookie = null;

    if (saveEmail) {
      // 클라이언트로 보낼 데이터인 쿠키에 이메일을 저장한다.
      cookie = new Cookie("userEmail", email);
    } else {
      cookie = new Cookie("userEmail", "");
      cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 삭제하도록 요구한다.
    }
    response.addCookie(cookie); // 응답할 때 쿠키 정보를 응답헤더에 포함시킨다.

    return new ResultMap().setStatus(SUCCESS);

  }

  @RequestMapping("/member/getLoginUser")
  public Object getLoginUser(HttpSession session) {
    Object sign = session.getAttribute("loginUser");
    if (sign != null) {
      return new ResultMap().setStatus(SUCCESS).setData(sign);
    } else {
      return new ResultMap().setStatus(FAIL).setData("로그인 하지 않았습니다.");
    }
  }
  // sign -> member로 바꿈

  @RequestMapping("/member/signout")
  public Object signout(HttpSession session) {
    session.invalidate();
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/member/findid")
  public Object findid(String name, String tel) {
    Member findid = memberService.find(name, tel);
    if (findid == null) {
      return 0;
    }
    return memberService.find(name, tel).getEmail();
  }

  @RequestMapping("/member/findpwd")
  public Object findpwd(String email, String tel) {
    Member findpwd = memberService.send(email, tel);
    System.out.println("///////////////////////////");
    System.out.println(findpwd);
    System.out.println("///////////////////////////");
    if (findpwd == null) {
      return 0;
    }
    MemberDto em = memberService.createMailandChangePassword(email);
    if (em == null) {
      return 0;
    }
    memberService.mailSend(em);
    return 1;

  }

  @RequestMapping("/member/checkemail")
  public Object findemail(String email) {
    Member ckeckemail = memberService.send(email);
    if (ckeckemail == null) {
      return 0;
    }
    return 1;

  }

  @RequestMapping("/member/facebookLogin")
  public Object facebookLogin(String accessToken, HttpSession session) {

    // 1) accessToken을 가지고 페이스북으로 가서 로그인 사용자 정보를 가져온다.
    RestTemplate restTemplate = new RestTemplate();
    Map<String, String> result = restTemplate.getForObject(
        "https://graph.facebook.com/v13.0/me?access_token={value1}&fields={value2}", // 요청할 URL
        Map.class, // 서버에서 받은 결과의 타입
        accessToken, // URL의 첫 번째 자리에 들어갈 값
        "id,name,email,gender" // 페이스북 측에 요청하는 로그인 사용자 정보
        );
    System.out.println("value" + restTemplate);
    System.out.println("value" + result);
    // 2) 사용자 이름과 이메일을 알아낸다.
    String name = result.get("name");
    String email = result.get("email");

    // 3) 현재 등록된 사용자 중에서 해당 이메일의 사용자가 있는지 찾아본다.
    Member member = memberService.get(email);
    System.out.println("value" + member);

    if (member != null) {
      // 4-1) 등록된 사용자가 있다면 그 사용자로 자동 로그인 처리한다.
      session.setAttribute("loginUser", member);
      return new ResultMap().setStatus(SUCCESS).setData("Y");

    } else {
      // 4-2) 등록된 사용자가 아니라면 회원 등록 후 자동 로그인 처리한다.
      //            memberService.add(new Member().setEmail(email).setMemberName(name).setPassWord("1111"));
      //            session.setAttribute("loginUser", memberService.get(email));
      return new ResultMap().setStatus(SUCCESS).setData(email + "____" + name);
    }
  }

  // 카카오 이메일 중복체크
  @RequestMapping(value = "/member/kakaoLogin")
  public Object kakaoLogin(String email, HttpSession session) {
    // 3) 현재 등록된 사용자 중에서 해당 이메일의 사용자가 있는지 찾아본다.
    Member member = memberService.get(email);
    System.out.println("value" + member);

    if (member != null) {
      // 4-1) 등록된 사용자가 있다면 그 사용자로 자동 로그인 처리한다.
      session.setAttribute("loginUser", member);
      return new ResultMap().setStatus(SUCCESS).setData("Y");

    } else {
      // 4-2) 등록된 사용자가 아니라면 회원 등록 후 자동 로그인 처리한다.
      //                    memberService.add(new Member().setEmail(email).setMemberName(name).setPassWord("1111"));
      //                    session.setAttribute("loginUser", memberService.get(email));
      return new ResultMap().setStatus(SUCCESS).setData("N");
    }
  }

  // 구글 이메일 중복체크
  @RequestMapping(value = "/member/googleLogin")
  public Object googleLogin(String email, HttpSession session) {
    // 3) 현재 등록된 사용자 중에서 해당 이메일의 사용자가 있는지 찾아본다.
    Member member = memberService.get(email);
    System.out.println("value" + member);

    if (member != null) {
      // 4-1) 등록된 사용자가 있다면 그 사용자로 자동 로그인 처리한다.
      session.setAttribute("loginUser", member);
      return new ResultMap().setStatus(SUCCESS).setData("Y");

    } else {
      // 4-2) 등록된 사용자가 아니라면 회원 등록 후 자동 로그인 처리한다.
      //                    memberService.add(new Member().setEmail(email).setMemberName(name).setPassWord("1111"));
      //                    session.setAttribute("loginUser", memberService.get(email));
      return new ResultMap().setStatus(SUCCESS).setData("N");
    }
  }







  //추가
  @RequestMapping("/member/list")
  public Object memberList() { 
    return memberService.list();
  }
}
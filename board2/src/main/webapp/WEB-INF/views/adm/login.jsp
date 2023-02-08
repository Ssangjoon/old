<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="login_wrap">
	<div class="inbox">
		
		<div class="tt">
			<img src="images/logo.png" alt="logo">
			<p>관리자 전용 메뉴</p>
		</div>
		<form action='/adm/loginCheck.do' method="post">
			<ul>
				<li><input type="text" name="uid"></li>
				<li><input type="password" name="upw"></li>
			</ul>
			<input type="submit" class="btn" value="로그인">
			<div class="fail_msg"></div>
		</form>
		<div class="copy">Copyright. Allgill.  All rights reserved.</div>
	</div>
</div>

<script type="text/javascript">
//<![CDATA[
switch ('${rt}') {
case "1":
	$(".fail_msg").html("아이디 또는 비밀번호가 일치하지 않습니다.");
	break;
case "2":
	alert("계정이 비활성화 되어 있습니다.\n관리담당자로 문의해주세요.");
	$(".fail_msg").html("계정이 비활성화 되어 있습니다.");
	break;
case "3": 
	$(".fail_msg").html("잘못된 접근 또는 응답대기 시간 초과로 자동 로그아웃 되었습니다.");
	break;
case "4": 
	alert("메일이 전송되었습니다.");
	break;
}
//]]>
</script>
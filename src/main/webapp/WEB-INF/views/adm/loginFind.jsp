<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="login_wrap">
	<div class="inbox">
		
		<div class="tt">
			<img src="images/logo.png" alt="logo">
			<p>관리자 계정 찾기</p>
		</div>
		<div class="dec_msg">등록된 이메일 번호를 입력하시면 계정과 임시 비밀번호를 발송해 드립니다.</div>
		<form action='/adm/mailSender.do' method="post">
			<ul>
				<li><input type="text" name="email" class="email"></li>
			</ul>
			<input type="submit" class="btn" value="전송">
			<div class="fail_msg"></div>
		</form>
		<div class="copy">Copyright. Allgill.  All rights reserved.</div>
	</div>
</div>

<script type="text/javascript">
//<![CDATA[
switch ('${rt}') {
case "1":
	$(".fail_msg").html("일치하는 관리자 메일 계정이 없습니다.");
	break;
case "2":
	$(".fail_msg").html("메일 전송에 실패하였습니다.");
	break;
}
//]]>
</script>
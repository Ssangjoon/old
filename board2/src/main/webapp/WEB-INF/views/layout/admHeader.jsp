<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="header_wrap">
	<div class="header">
		<div class="title">MANAGEMENT SYSTEM</div>
		<div class="log">
			<span>${user.name}</span>님 <button id="logout">로그아웃</button>
		</div>
	</div>
</div>
<div class="sidemenu">
	<ul>
		<li class="${gNum eq'01'?'on':''}"><a href="/adm/member.do"><span>회원관리</span></a></li>
		<li class="${gNum eq'02'?'on':''}"><a href="/adm/notice.do"><span>공지사항</span></a></li>
		<li class="${gNum eq'03'?'on':''}"><a href="/adm/faq.do"><span>FAQ</span></a></li>
		<li class="${gNum eq'04'?'on':''}"><a href="/adm/idea.do"><span>아이디어</span></a></li>
	</ul>
</div>

adm/logout.do

<script>
$("#logout").click(function(){
	var con_test = confirm("로그아웃 하시겠습니까?");
	if(con_test == false){
	  return false;
	}else{
		logout();
	}
})

function logout(){
	location.replace("/adm/logout.do");
}


</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
	<div class="inner">
		<h1>게시판 연습</h1>
		<a href="javascript:void(0);" class="btn_menu">
			<span class="t"></span>
			<span class="m"></span>
			<span class="b"></span>
		</a>
		<div class="gnb">
			<div class="black"></div>
			<ul class="menu">
				<li class="${gNum eq'01'?'on':''}">
					<a href="/customer/notice.do" class="${gNum eq'04' && sNum eq'02'?'on':''}">공지사항</a>
					<a href="/customer/faq.do" class="${gNum eq'04' && sNum eq'03'?'on':''}">FAQ</a>
				</li>
			</ul>
		</div>
	</div>
</div>
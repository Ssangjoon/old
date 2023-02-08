<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="faq-top">
		<ul>
			<li class="${pageType eq null?'on':''}"><a href="/customer/faq.do">top10</a></li>
			<li class="${pageType eq'결제'?'on':''}"><a href="/customer/faq.do?pageType=결제">결제</a></li>
			<li class="${pageType eq'지급'?'on':''}"><a href="/customer/faq.do?pageType=지급">지급</a></li>
			<li class="${pageType eq'기술'?'on':''}"><a href="/customer/faq.do?pageType=기술">기술</a></li>
			<li class="${pageType eq'계약/변경'?'on':''}"><a href="/customer/faq.do?pageType=계약/변경">계약/변경</a></li>
			<li class="${pageType eq'기타'?'on':''}"><a href="/customer/faq.do?pageType=기타">기타</a></li>
		</ul>

	</div>

	<div class="faq">
		<c:forEach items="${list}" var="list">
			<dl>
				<dt><c:out value="${list.title}" /><i></i></dt>
				<dd><c:out value="${list.content}" /></dd>
			</dl>
		</c:forEach>
	</div>

		<div class="board_bottom">
			<div class="paging">
				<c:if test="${paging.startPage != 1 }">
					<a href="/customer/notice.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&lt;&lt;</a>
				</c:if>
				<c:if test="${paging.nowPage != 1 }">
					<a href="/customer/notice.do?nowPage=${paging.nowPage - 1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&lt;</a>
				</c:if>
				<span class="nums">
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage && pageType != null}">
								<a href="#this" class="on">${p }</a>
							</c:when>
							<c:when test="${p != paging.nowPage }">
								<a href="/customer/notice.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
							</c:when>
						</c:choose>
					</c:forEach>
				</span>
				<c:if test="${paging.nowPage != paging.lastPage && paging.lastPage != 0}">
					<a href="/customer/notice.do?nowPage=${paging.nowPage+1 }&cntPerPage=${paging.cntPerPage}" class="arrow bdl">&gt;</a>
				</c:if>
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/customer/notice.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&gt;&gt;</a>
				</c:if>
			</div>
		
			<div class="search_wrap">
				<form action="/customer/faq.do" method="get">
					<select name="search_option" id="sfl" class="text">
						<option value="1" ${option eq'1'?'selected':''}>제목</option>
						<option value="2" ${option eq'2'?'selected':''}>내용</option>
					</select>
					<input type="text" name="search_key" value="${keyword}"  class="text" maxlength="20">
					<input type="submit" value="검색" class="btn">
				</form>
			</div>
		</div>
 <!-- //board_bottom -->
	

<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){
	$(".faq dt").click(function(){
		$(this).toggleClass("on").next("dd").slideToggle("fast").parent().siblings().children("dt").removeClass("on").next("dd").slideUp("fast");
	});
});
//]]>
</script>
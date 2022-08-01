<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

		<div class="board_list">
			<table>
				<tr>
					<th class="w1 pc_vw">번호</th>
					<th>제목</th>
					<th class="w2 pc_vw">작성자</th>
					<th class="w3">작성일</th>
					<th class="w2 pc_vw">조회수</th>
				</tr>
				<c:forEach items="${list}" var="list">
					<tr>
						<td class="w1 pc_vw"><c:out value="${list.seq}" /></td>
						<td class="tal"><a href="/customer/noticeDetail.do?seq=${list.seq}"><c:out value="${list.title}" /></a></td>
						<td class="w2 pc_vw"><c:out value="${list.writer}" /></td>
						<td><c:out value="${list.crt_dt}" /></td>
						<td class="w2 pc_vw"><c:out value="${list.view_count}" /></td>
					</tr>
				</c:forEach>
			</table>
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
							<c:when test="${p == paging.nowPage }">
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
				<form action="/customer/notice.do" method="get">
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

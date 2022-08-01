<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="title">회원관리</div>
	<div class="content">

		<div class="select_wrap">
			<form action="/adm/member.do" method="get">
				<select name="search_option">
					<option value="1" ${option eq'1'?'selected':''}>아이디</option>
					<option value="2" ${option eq'2'?'selected':''}>이름</option>
				</select>
				<input type="text" name="search_key" value="${keyword}">
				<input type="submit" value="검색">
			</form>
		</div>
		<div class="board_list">
			<table>
				<colgroup>
					<col width="100">
					<col width="120">
					<col width="100">
					<col width="150">
					<col width="200">
					<col width="300">
					<col width="100">
					<col width="200">
					<col width="120">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>연락처</th>
					<th>이메일 주소</th>
					<th>메모</th>
					<th>상태</th>
					<th>마지막 로그인</th>
					<th>관리</th>
				</tr>
				<c:forEach items="${list}" var="list">
					<tr>
						<td><c:out value="${list.seq}" /></td>
						<td><c:out value="${list.uid}" /></td>
						<td><c:out value="${list.name}" /></td>
						<td><c:out value="${list.contact}" /></td>
						<td><c:out value="${list.email}" /></td>
						<td><c:out value="${list.memo}" /></td>
						<td><c:out value="${list.state eq'1'?'활성':'비활성'}" /></td>
						<td><c:out value="${list.last_login_dt}" /></td>
						<td><a href="/adm/memModify.do?uid=${list.uid}">수정</a> / <button onclick="memDelete('${list.uid}');">삭제</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="board_bottom">
			<div class="btns">
				<div class="fr">
					<a href="memWrite.do" class="btn">회원등록</a>
				</div>
			</div>
			<div class="paging">
				<c:if test="${paging.startPage != 1 }">
					<a href="/adm/member.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&lt;&lt;</a>
				</c:if>
				<c:if test="${paging.nowPage != 1 }">
					<a href="/adm/member.do?nowPage=${paging.nowPage - 1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&lt;</a>
				</c:if>
				<span class="nums">
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage }">
								<a href="#this" class="on">${p }</a>
							</c:when>
							<c:when test="${p != paging.nowPage }">
								<a href="/adm/member.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
							</c:when>
						</c:choose>
					</c:forEach>
				</span>
				<c:if test="${paging.nowPage != paging.lastPage && paging.lastPage != 0}">
					<a href="/adm/member.do?nowPage=${paging.nowPage+1 }&cntPerPage=${paging.cntPerPage}" class="arrow bdl">&gt;</a>
				</c:if>
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/adm/member.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&gt;&gt;</a>
				</c:if>
			</div>
		</div>

	</div>

	<script>
	function memDelete(uid){
		var con_test = confirm("삭제하시겠습니까?");
		if(con_test == false){
		  return false;
		}else{
			deleteAdm(uid);
		}		
	}
	
	function deleteAdm(uid){
		$.ajax({
		    url:'/adm/memDelete.do',
		    type:'post',
		    data:{
				'uid': uid, 
		    	},
		    success: function(data) {
		        console.log(data)
		        alert("삭제되었습니다.");
		        location.replace("/adm/member.do");
		    },
		    error: function(err) {
		        alert(err);
		    }
		});
	}
	</script>
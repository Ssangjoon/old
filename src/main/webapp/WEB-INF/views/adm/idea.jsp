<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="title">아이디어</div>
	<div class="content">

		<div class="select_wrap">
			<form action="/adm/idea.do" method="get">
				<select name="search_option">
					<option value="1" ${option eq'1'?'selected':''}>제목</option>
					<option value="2" ${option eq'2'?'selected':''}>내용</option>
				</select>
				<input type="text" name="search_key" value="${keyword}">
				<input type="submit" value="검색">
			</form>
		</div>
		<div class="board_list">
			<table>
				<colgroup>
					<col width="100">
					<col width="300">
					<col width="300">
					<col width="130">
					<col width="130">
					<col width="120">
				</colgroup>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>관리</th>
				</tr>
				<c:forEach items="${list}" var="list">
					<tr>
						<td><c:out value="${list.seq}" /></td>
						<td><c:out value="${list.title}" /></td>
						<td><c:out value="${list.content}" /></td>
						<td><c:out value="${list.writer}" /></td>
						<td><c:out value="${list.crt_dt}" /></td>
						<td><a href="/adm/ideaModify.do?seq=${list.seq}">수정</a> / <button onclick="ideaDelete('${list.seq}');">삭제</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="board_bottom">
			<div class="btns">
				<div class="fr">
					<a href="ideaWrite.do" class="btn">글 등록</a>
				</div>
			</div>
			<div class="paging">
				<c:if test="${paging.startPage != 1 }">
					<a href="/adm/idea.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&lt;&lt;</a>
				</c:if>
				<c:if test="${paging.nowPage != 1 }">
					<a href="/adm/idea.do?nowPage=${paging.nowPage - 1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&lt;</a>
				</c:if>
				<span class="nums">
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage }">
								<a href="#this" class="on">${p }</a>
							</c:when>
							<c:when test="${p != paging.nowPage }">
								<a href="/adm/idea.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
							</c:when>
						</c:choose>
					</c:forEach>
				</span>
				<c:if test="${paging.nowPage != paging.lastPage && paging.lastPage != 0}">
					<a href="/adm/idea.do?nowPage=${paging.nowPage+1 }&cntPerPage=${paging.cntPerPage}" class="arrow bdl">&gt;</a>
				</c:if>
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/adm/idea.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}" class="arrow">&gt;&gt;</a>
				</c:if>
			</div>
		</div>

	</div>
	<script>
	function ideaDelete(seq){
		var con_test = confirm("삭제하시겠습니까?");
		if(con_test == false){
		  return false;
		}else{
			deleteAdm(seq);
		}		
	}
	
	function deleteAdm(seq){
		$.ajax({
		    url:'/adm/ideaDelete.do',
		    type:'post',
		    data:{
				'seq': seq, 
		    	},
		    success: function(data) {
		        console.log(data)
		        alert("삭제되었습니다.");
		        location.replace("/adm/idea.do");
		    },
		    error: function(err) {
		        alert(err);
		    }
		});
	}
	</script>
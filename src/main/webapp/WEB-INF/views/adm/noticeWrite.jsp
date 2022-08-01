<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="title">공지사항</div>
	<form action='/adm/noticeInsert.do' method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
	<div class="content">
			<div class="board_write">
				<table>
					<colgroup>
						<col width="230">
						<col width="*">
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input type="text" class="w1" name="title" id="title"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" cols="30" rows="10" class="w100p" id="content"></textarea></td>
					</tr>
					<tr>
						<th>첨부파일 1</th>
						<td><input type="file" name="file"></td>
					</tr>
					<tr>
						<th>첨부파일 2</th>
						<td><input type="file" name="file"></td>
					</tr>
					<tr>
						<th>첨부파일 3</th>
						<td><input type="file" name="file"></td>
					</tr>
					<tr>
						<th>첨부파일 4</th>
						<td><input type="file" name="file"></td>
					</tr>
				</table>
			</div>
			<div class="board_bottom">
				<div class="btns tac">
					<a href="/adm/notice.do" class="btn btn_g">취소</a>
					<input type="submit" class="btn" value="저장">
				</div>
			</div> <!-- //board_bottom -->
		</div>
	</form>

<script type="text/javascript">
//<![CDATA[
switch ('${rt}') {
case "1":
	alert("저장되었습니다.");
	location.replace("/adm/notice.do");
	break;
}
	
function checkForm(){
	if($("#title").val() == ""){
		alert("제목은 필수 입력값입니다.");
		$("#title").focus();
		return false;
	}
	
	if($("#content").val() == ""){
		alert("내용은 필수 입력값입니다.");
		$("#content").focus();
		return false;
	}
	
	return confirm("저장하시겠습니까?");
}
//]]>
</script>
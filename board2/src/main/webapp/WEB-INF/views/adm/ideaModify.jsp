<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="title">아이디어</div>
	<form action='/adm/ideaUpdate.do' method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
	<div class="content">
			<div class="board_write">
				<table>
					<colgroup>
						<col width="230">
						<col width="*">
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input type="text" class="w1" name="title" id="title" value="${list.title}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" cols="30" rows="10" class="w100p" id="content">${list.content}</textarea></td>
					</tr>
					
					<c:forEach var="i" begin="0" end="3">
						<tr>
							<th>첨부파일 ${i + 1}</th>
							<c:if test="${not empty file[i].org_file}">
								<td id="file${file[i].seq}"><a href="/adm/fileDown.do?file_seq=${file[i].seq}">${file[i].org_file}</a> (${file[i].file_size} kb) <span class="del_btn" onclick="delFile('${file[i].seq}')">삭제</span></td> 
							</c:if>
							<c:if test="${empty file[i].org_file}">
								<td><input type='file' name='file'></td> 
							</c:if>  
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="seq" id="seq" value="${list.seq}" />
				<input type="hidden" name="writer" id="writer" value="${user.name}" />
			</div>
			<div class="board_bottom">
				<div class="btns tac">
					<a href="/adm/idea.do" class="btn btn_g">취소</a>
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
	location.replace("/adm/idea.do");
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

function delFile(seq){
	var con_test = confirm("파일은 바로 삭제됩니다.\n삭제하시겠습니까?");
	if(con_test == false){
	  return false;
	}else{
		deleteFile(seq);
	}
}


function deleteFile(seq){

	$.ajax({
	    url:'/adm/deleteFile.do',
	    type:'post',
	    data:{
	    	'seq' : seq,
	    	},
	    success: function(data) {
	        console.log(data)
	        alert("삭제되었습니다.");
	        $("#file"+seq).html("<input type='file' name='file'>");
	    },
	    error: function(err) {
	        alert(err);
	    }
	});
}

//]]>
</script>
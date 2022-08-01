<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="title">FAQ</div>
	<div class="content">

		<div class="board_write">
			<table>
				<colgroup>
					<col width="230">
					<col width="*">
				</colgroup>
				<tr>
					<th>카테고리</th>
					<td>
						<select id="category" class="w3">
							<option value="결제" ${list.category eq'결제'?'selected':''}>결제</option>
							<option value="지급" ${list.category eq'지급'?'selected':''}>지급</option>
							<option value="기술" ${list.category eq'기술'?'selected':''}>기술</option>
							<option value="계약/변경" ${list.category eq'계약/변경'?'selected':''}>계약/변경</option>
							<option value="기타" ${list.category eq'기타'?'selected':''}>기타</option>
						</select>				
					</td>
				</tr>
				<tr>
					<th>질문</th>
					<td><input type="text" id="title" class="w100p" value="${list.title}"></td>
				</tr>
				<tr>
					<th>답변</th>
					<td><textarea name="" id="content" cols="30" rows="10" class="w100p">${list.content}</textarea></td>
				</tr>
				<tr>
					<th>노출순서</th>
					<td><input type="text" id="sort_num" class="w50p" value="${list.sort_num}">
					</td>
				</tr>
			</table>
		</div>
		<div class="board_bottom">
			<div class="btns tac">
				<a href="/adm/faq.do" class="btn btn_g">취소</a>
				<input type="submit" id="submit" class="btn" value="저장">
			</div>
		</div> <!-- //board_bottom -->

	</div>


<script type="text/javascript">
//<![CDATA[
//이메일 입력방식 선택
$('#submit').click(function(){
	if($("#title").val() == ""){
		alert("질문은 필수 입력값입니다.");
		$("#title").focus();
		return false;
	}
	
	if($("#content").val() == ""){
		alert("답변은 필수 입력값입니다.");
		$("#content").focus();
		return false;
	}
	
	var con_test = confirm("수정하시겠습니까?");
	if(con_test == false){
	  return false;
	}else{
		insertAdm();
	}
	
});

function insertAdm(){
	var sort_num = $("#sort_num").val();
	if(sort_num == ""){
		sort_num = 0;
	}else{
		sort_num = $("#sort_num").val();
	}
	
	$.ajax({
	    url:'/adm/faqUpdate.do',
	    type:'post',
	    data:{
	    	'seq' : '${list.seq}',
			'category': $("#category").val(), 
		    'title' : $("#title").val(), 
		    'content' :$("#content").val(), 
		    'sort_num' : sort_num
	    	},
	    success: function(data) {
	        console.log(data)
	        alert("수정되었습니다.");
	        location.replace("/adm/faq.do");
	    },
	    error: function(err) {
	        alert(err);
	    }
	});
}
//]]>
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="title">회원관리</div>
	<div class="content">
		<div class="board_write">
			<table>
				<colgroup>
					<col width="230">
					<col width="*">
				</colgroup>
				<tr>
					<th>아이디</th>
					<td>${list.uid}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="w1" id="upw"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" class="w1" id="upwCheck"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" class="w1" id="name" value="${list.name}"></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><input type="text" class="w1" id="contact" value="${list.contact}"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" class="w3" id="emailId" value="${list.email}">
					</td>
				</tr>
				<tr>
					<th>메모</th>
					<td><input type="text" class="w1" id="memo" value="${list.memo}"></td>
				</tr>
				<tr>
					<th>상태</th>
					<td>
						<select id="state" class="w3">
							<option value="1" ${list.state eq'1'?'selected':''}>활성</option>
							<option value="0" ${list.state eq'0'?'selected':''}>비활성</option>
						</select>				
					</td>
				</tr>
			</table>
			
		</div>
		<div class="board_bottom">
			<div class="btns tac">
				<a href="/adm/member.do" class="btn btn_g">취소</a>
				<input type="submit" id="submit" class="btn" value="저장">
			</div>
		</div> <!-- //board_bottom -->
	</div>

<script type="text/javascript">
//<![CDATA[
//이메일 입력방식 선택
$('#selectEmail').change(function(){
	$("#selectEmail option:selected").each(function () {
		if($(this).val()== '1'){ //직접입력일 경우
			$("#str_email02").val(''); //값 초기화
			$("#str_email02").attr("disabled",false); //활성화
		}else{ //직접입력이 아닐경우
			$("#str_email02").val($(this).text()); //선택값 입력
			$("#str_email02").attr("disabled",true); //비활성화
		}
	});
});

$('#submit').click(function(){
	if($("#uid").val() == ""){
		alert("아이디는 필수 입력값입니다.");
		$("#uid").focus();
		return false;
	}
	
	if($("#upw").val() == ""){
		alert("비빌번호는 필수 입력값입니다.");
		$("#upw").focus();
		return false;
	}
	
	if($("#emailId").val() == "" ||  $("#str_email02").val() == ""){
		alert("이메일은 필수 입력값입니다.");
		$("#emailId").focus();
		return false;
	}
	
	if($("#upw").val() != $("#upwCheck").val()){
		alert("비빌번호 확인이 일치하지 않습니다.");
		$("#upwCheck").focus();
		return false;
	}
	
	if(!chkPwd( $.trim($('#upw').val()))){
	   $('#upw').val('');
	   $('#upw').focus();
	   return false;
	}
	
	var con_test = confirm("수정하시겠습니까?");
	if(con_test == false){
	  return false;
	}else{
		updateAdm();
	}
	
});

function updateAdm(){
	$.ajax({
	    url:'/adm/memUpdate.do',
	    type:'post',
	    data:{
			'uid': "${list.uid}", 
		    'name' : $("#name").val(), 
		    'contact' : $("#contact").val(),
		    'email' : $("#emailId").val(),
		   	'memo' : $("#memo").val(),
		    'state': $("#state").val(),
		    'upw' : $("#upw").val(),
	    	},
	    success: function(data) {
	        console.log(data)
	        alert("수정되었습니다.");
	        location.replace("/adm/member.do");
	    },
	    error: function(err) {
	        alert(err);
	    }
	});
}

function chkPwd(str){
	 var pw = str;
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 if(pw.length < 8 || pw.length > 20){
	  alert("8자리 ~ 20자리 이내로 입력해주세요.");
	  return false;
	 }
	 
	 if(pw.search(/₩s/) != -1){
	  alert("비밀번호는 공백없이 입력해주세요.");
	  return false;
	 } if(num < 0 || eng < 0 || spe < 0 ){
	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
	  return false;
	 }

	 return true;
	}

//]]>
</script>
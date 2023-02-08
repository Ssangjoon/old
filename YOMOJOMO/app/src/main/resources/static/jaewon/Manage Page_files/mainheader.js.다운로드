
css(".login", "display", "none");

fetch("/member/getLoginUser").then(function(response) {
	return response.json();
}).then(function(result) {
	if (result.status == "success") {
		css(".login", "display", "");
		css(".not-login", "display", "none");
	}
});

function css(selector, name, value) {
	var el = document.querySelectorAll(selector);
  for (var e of el) {
    e.style[name] = value;
  }
}


document.querySelector("#logout-btn").onclick = function() {
  fetch("/member/signout").then(function(response) {
	  location.reload();
  });
};




$.ajax({ //로그인 회원 정보 가져오기
	url : "/member/getLoginUser",
	type : "POST",
	datatype : "json",
	success : function(result) {
		const memberNo = result.data.no;
		if (result.status == "fail") {
			$("#alert-msg-li").hide();
			$("#createGroupli").hide();
		}
		// console.log(memberNo);
		$.ajax({ //받은 초대 메세지 가져오기
			url: "/invitebox/mnolist",
			type: "POST",
			datatype: "json",
			data : {"member.no" : memberNo},
			success: function (result) {
				if (result.data.length > 0) {
					$("#invitemsgCnt").text(result.data.length);
					$("#totalMsgCnt").text(result.data.length); //나중에 가입신청서 숫자랑 더해야함
						//상준님 여기에 서버에 메세지 달라고 요청하세요
					
					
				}
			}//받은 초대 메세지 가져오기 success END		
		})// 초대 메세지 가져오기 END
	}//로그인정보 가져오기 END
}); //로그인 여부 확인 ajax END

document.querySelector('.btn-group-link').addEventListener('click', function() {
	window.location.href = '/ssang/group/form.html';
})



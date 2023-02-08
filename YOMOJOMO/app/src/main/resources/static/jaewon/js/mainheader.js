
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



/*
$.ajax({ //로그인 회원 정보 가져오기
	url : "/member/getLoginUser",
	type : "POST",
	datatype : "json",
	success : function(result) {
		const memberNo = result.data.no;
		var suceesSignIn = document.querySelector("#sign-up")
		suceesSignIn.classList.add('suceess-sign-in')
		if (result.status == "fail") {
			$("#alert-msg-li").hide();
			$("#createGroupli").hide();
			suceesSignIn.classList.remove('suceess-sign-in')
			css(".suceess-sign-in", "display", "block")
		}
		window.loginUser = result.data;
		
		css(".suceess-sign-in", "display", "none")

		// console.log(memberNo);
		$.ajax({ //받은 초대 메세지 가져오기
			url: "/invitebox/invitelistbyrecipient",
			type: "POST",
			datatype: "json",
			data : {"member.no" : memberNo},
			success: function (result) {
				var msgResult = result.data;
				console.log(msgResult)
				var msgCount = 0;

				for (i = 0; i < msgResult.length; i++) {
					if (msgResult[i].confirm == false) {
						msgCount++;
					}
				}

				if (msgResult.length > 0) {
					console.log("참");
					$("#invitemsgCnt").text(msgCount);
					$("#totalMsgCnt").text(msgCount); //!!!!!!!!!!!!!!!!!!!나중에 모임 가입신청서 숫자랑 더해야함!!!!!!!!!!!!!!!!!!!!!!!
				}
			}//받은 초대 메세지 가져오기 success END		
		})// 초대 메세지 가져오기 END

		$.ajax({ //모임가입요청 가져오기
			url: "/applyFixedAnswer/findRequestByMasNO",
			type: "POST",
			datatype: "json",
			data : {"master.no" : memberNo},
			success: function (result) {
				var msgResult = result.data;
				console.log(msgResult)
				var msgCount2 = 0;
				for (i = 0; i < msgResult.length; i++) {
					if (msgResult[i].confirm == false) {
						msgCount2++;
					}
				}

				if (msgResult.length > 0) {
					console.log("참");
					$("#requestmsgCnt").text(msgCount2);
					
				}
			}//모임가입요청 가져오기 success END		
		})// 모임가입요청 가져오기 END
	}//로그인정보 가져오기 END
}); //로그인 여부 확인 ajax END
document.querySelector('.btn-group-link').addEventListener('click', function() {
	window.location.href = '/ssang/group/form.html';
})*/



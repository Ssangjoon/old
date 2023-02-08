// 일반 로그인
function login() {
	var xEmail = document.querySelector("input[name=email]");
	var xPassword = document.querySelector("input[name=password]");
	// form 데이터에 입력값을 submit한다.
	document.querySelector("form[name=formyo]").onsubmit = function() {
		if (xEmail.value == "" || xPassword.value == "") {
			window.alert("필수 입력 항목이 비어 있습니다.");
			return false;
		}
		// 폼데이터를 받아서 컨트롤러에서 체크한다.
		var fd = new FormData(document.forms.namedItem("formyo"));
		fetch("/member/signin", {
			method: "POST",
			body: new URLSearchParams(fd)
		}).then(function(response) {
			return response.json();
		}).then(function(result) {
			if (result.status == "success") {
				location.href="/jaewon/admin/index/index.html"
			} else {
				window.alert("관리자만 접근 가능합니다.")
			}
		});
		return false;
	};
}





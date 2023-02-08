function searchB() {
	var xEmail = document.querySelector("input[name=email]");
	var xTel = document.querySelector("input[name=tel]");

	document.querySelector("form[name=findpwd]").onsubmit = function() {
		if (xTel.value == "" || xEmail.value == "") {
			window.alert("필수 입력 항목이 비어 있습니다.");
			return false;
		}
		var fd = new FormData(document.forms.namedItem("findpwd"));

		fetch("/member/findpwd", {
			method: "POST",
			body: new URLSearchParams(fd)
		}).then(function(response) {
			return response.text();
		}).then(function(text) {
			if (text != 0) {
				$('.modal').show();
				$('.email').append("이메일로 임시비밀번호가 발송되었습니다.");
				$('.close').on('click', function() {
					$('.modal').hide();
					check = false;
					location.href = "/junho/index.html";
				});
				$('.btn-close').on('click', function() {
					$('.modal').hide();
					check = false;
					location.href = "/junho/index.html";
				});
				$(window).on('click', function() {
					if (event.target == $('.modal').get(0)) {
						$('.modal').hide();
						check = false;
						location.href = "/junho/index.html";
					}
				});
			} else {
				window.alert("없는 사용자거나 탈퇴한 사용자입니다.")
				location.reload();
			}
		});
		return false;
	};
}
function snsLoginDataCheck() {
	var cookieData = Cookies.get("snsLoginData")
	if (cookieData !== undefined) {
		var cookieJson = JSON.parse(cookieData)
		console.log(cookieJson.name)
		console.log(cookieJson.email)
		console.log(cookieJson.type)
		var xEmail = document.querySelector("input[name=email]")
		var xName = document.querySelector("input[name=memberName]");
		xEmail.value = cookieJson.email;
		xName.value = cookieJson.name;
		xEmail.readOnly = true;
		/*if (cookieJson.type !== "kakao") {
			xName.readOnly = true;	
		}*/
		xName.readOnly = cookieJson.type !== 'kakao';
		var sLB = document.querySelector("#snsLoginButtons");
		sLB.parentNode.removeChild(sLB);

	}
}


//document.querySelector("form[name=signup]").onsubmit = function() {
function signForm() {
	var xEmail = document.querySelector("input[name=email]");
	var xPassword = document.querySelector("input[name=passWord]");
	var xPasswordCheck = document.querySelector("input[name=passWordCheck]");
	var xName = document.querySelector("input[name=memberName]");
	var xTel = document.querySelector("input[name=tel]");
	var xPostNo = document.querySelector("input[name=postNo]");
	var xRoadNameAddress = document.querySelector("input[name=roadNameAddress]");
	var xBaseAddress = document.querySelector("input[name=baseAddress]");
	var xAddress = document.querySelector("input[name=address]");

	var validTargets = [xEmail, xPassword, xPasswordCheck, xName, xTel, xPostNo, xRoadNameAddress, xBaseAddress, xAddress];

	// xEmail.value === ''


	$('form').on('submit', function(e) {
		var xSelectLocal = $("#locallist").children().length;
		var xSelectPups = $("#pupslist").children().length;
		e.preventDefault();
		// !xEmail.value

		var isValid = true;

		validTargets.forEach(target => {
			if (!target) {
				isValid = false;
			}
		});
		if (isValid === false) {
			window.alert("필수 항목을 입력하세요")
			return false;
		}


		var emailTest = xEmail.value
		var pwdTest = xPassword.value
		var pwdCheckTest = xPasswordCheck.value
		var nameTest = xName.value;
		var telTest = xTel.value;
		var RNATest = xRoadNameAddress.value;
		var BATest = xBaseAddress.value;

		var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
		var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
		var strongRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		var regName = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/; // "|"를 사용
		var regRoadAddress = /(([가-힣A-Za-z·\d~\-\.]{2,}(로|길).[\d]+)|([가-힣A-Za-z·\d~\-\.]+(읍|동)\s)[\d]+)/
		var regBaseAddress = /(([가-힣]+(\\d{1,5}|\\d{1,5}(,|.)\\d{1,5}|)+(읍|면|동|가|리))(^구|)((\\d{1,5}(~|-)\\d{1,5}|\\d{1,5})(가|리|)|))([ ](산(\\d{1,5}(~|-)\\d{1,5}|\\d{1,5}))|)|$/

		if (regEmail.test(emailTest) !== true) {
			alert('올바른 이메일 형식으로 입력해주세요');
			return false;
		}

		if (strongRegex.test(pwdCheckTest) !== true) {
			alert("최소 하나의 문자, 숫자, 특수문자를 조합해주세요.\n8자 이상 12자 이하의 비밀번호로 작성해주세요")
			return false;
		}

		if (pwdCheckTest !== pwdTest) {
			alert("비밀번호가 일치하지 않습니다.")
			return false;
		}

		if (regName.test(nameTest) !== true) {
			alert("올바른 이름 형식을 기입해주세요.")
			return false;
		}

		if (regPhone.test(telTest) !== true) {
			alert('전화번호를 다시 확인해주세요');
			return false;
		}

		if (regRoadAddress.test(RNATest) !== true) {
			alert('도로명 주소를 다시 확인해주세요');
			return false;
		}

		if (regBaseAddress.test(BATest) !== true) {
			alert('지번 주소를 다시 확인해주세요');
			return false;
		}


		if (xSelectLocal < 1) {
			alert("활동 지역 항목을 기입하세요");
			return false;
		}

		if (xSelectPups < 1) {
			alert("활동 목적 항목을 기입하세요");
			return false;
		}

		fetch(`/member/checkemail?email=${xEmail.value}`)
			.then(function(response) {
				return response.json();
			})
			.then(function(result) {
				console.log(result)
				if (result === 1) {
					window.alert("이미 존재하는 아이디(이메일)입니다.")
				} else {
					sendForm();
				}
			})
	})
}

function sendForm() {
	var fd = new FormData(document.forms.namedItem("signup"));
	fd.append('membLevel', '1');
	fd.append('unsubscribe', '0');
	var xLocallist = document.querySelector("#locallist")
	for (var i = 0; i < xLocallist.children.length; i++) {
		var value = xLocallist.children[i].value
		fd.append('localsArr', value);
	}
	var xPupslist = document.querySelector("#pupslist")
	for (var i = 0; i < xPupslist.children.length; i++) {
		var value = xPupslist.children[i].value
		fd.append('pupsArr', value);
	}



	fetch("/member/signup", {
		method: "POST",
		body: new URLSearchParams(fd)
	}).then(function(response) {
		return response.json();
	}).then(function(result) {
		if (result.status == "success") {
      alert("회원 가입이 완료되었습니다. 다시 로그인해주세요")
			location.href = "/junho/index.html";
		} else {
			window.alert("회원가입 실패!")
		}
	});
	return false;
}




function localListSi() {
	var locallistsi = document.querySelector("#localboard");
	var Selectsi = document.querySelector("#select-si");
	var htmlGenerators = Handlebars.compile(Selectsi.innerHTML);
	fetch("/activeLocal/list")
		.then(function(response) {
			return response.json();
		}).then(function(result) {
			if (result.status == "fail") {
				window.alert("서버 요청 오류!");
				console.log(result.data);
				return;
			}
			locallistsi.innerHTML = htmlGenerators(result);
		});
}

function localListGu() {
	var locallistgu = document.querySelector("#locallistgu");
	var Selectgu = document.querySelector("#select-gu");
	var htmlGeneratorg = Handlebars.compile(Selectgu.innerHTML);
	$('#localboard').change(function() {
		var gu = $("#localboard option:selected").val()
		var qs = `nameGu=${gu}`
		fetch(`/activeLocal/list-gu?${qs}`)
			.then(function(response) {
				return response.json();
			}).then(function(result) {
				if (result.status == "fail") {
					window.alert("서버 요청 오류!");
					console.log(result.data);
					return;
				}
				locallistgu.innerHTML = htmlGeneratorg(result);
			});
	})
}

function addLocal() {
	var locallist = document.querySelector("#locallist");
	var selectlocal = document.querySelector("#selectlocal");
	var htmlGeneratorselect = Handlebars.compile(selectlocal.innerHTML);

	$('.lo-btn').on('click', (e) => {
		var gu = $("#locallistgu option:selected").val()
		if (!gu) {
			alert("시/구를 입력해주세요");
			return false
		}
		var qs = `no=${gu}`
		fetch(`/activeLocal/get?${qs}`)
			.then(function(response) {
				return response.json();
			}).then(function(result) {
				if (result.status == "fail") {
					window.alert("서버 요청 오류!");
					console.log(result.data);
					return;
				}
				var localchild = $(locallist).children()
				for (var i = 0; i < localchild.length; i++) {
					if (localchild[i].value == result.no) {
						console.log(result.no)
						alert("중복된 지역은 추가할 수 없습니다.")
						return false;
					}
				}

				if (localchild.length > 2) {
					alert("3개 이상은 등록할 수 없습니다.")
					return false;
				}

				locallist.innerHTML += htmlGeneratorselect(result);
			});
	})
}

function deleteLocal() {
	$(document).on("click", "#locallist", (e) => {
		e.preventDefault();
		alert('삭제되었습니다.');
		$("#locals").remove();
	})

}

function pupsList() {
	var pupslist = document.querySelector("#purpose");
	var Selectpups = document.querySelector("#select-pups");
	var htmlGeneratorp = Handlebars.compile(Selectpups.innerHTML);
	fetch("/purpose/list")
		.then(function(response) {
			return response.json();
		}).then(function(result) {
			if (result.status == "fail") {
				window.alert("서버 요청 오류!");
				console.log(result.data);
				return;
			}
			pupslist.innerHTML = htmlGeneratorp(result);
		});
}

function addPups() {
	var pupslist = document.querySelector("#pupslist");
	var Selectpups = document.querySelector("#selectpups");
	var htmlGeneratorpups = Handlebars.compile(Selectpups.innerHTML);
	$('.pur-btn').on('click', (e) => {
		var pups = $("#purpose option:selected").val()
		if (!pups) {
			alert("시/구를 입력해주세요");
			return false
		}
		var qs = `no=${pups}`
		fetch(`/purpose/get?${qs}`)
			.then(function(response) {
				return response.json();
			}).then(function(result) {
				if (result.status == "fail") {
					window.alert("서버 요청 오류!");
					console.log(result.data);
					return;
				}
				var pupslistchild = $(pupslist).children()
				for (var i = 0; i < pupslistchild.length; i++) {
					if (pupslistchild[i].value == result.no) {
						console.log(result.no)
						alert("중복된 목적은 추가할 수 없습니다.")
						return false;
					}
				}

				if (pupslistchild.length > 2) {
					alert("3개 이상은 등록할 수 없습니다.")
					return false;
				}

				pupslist.innerHTML += htmlGeneratorpups(result);
			});
	})
}

function deletePups() {
	$(document).on("click", "#pupslist", (e) => {
		e.preventDefault();
		alert('삭제되었습니다.');
		$("#pups").remove();
	})

}

function searchAddress() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bnam !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample4_postcode').value = data.zonecode;
			document.getElementById("sample4_roadAddress").value = roadAddr;
			document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

			// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			if (roadAddr !== '') {
				document.getElementById("sample4_extraAddress").value = extraRoadAddr;
			} else {
				document.getElementById("sample4_extraAddress").value = '';
			}

			var guideTextBox = document.getElementById("guide");
			// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			if (data.autoRoadAddress) {
				var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				guideTextBox.style.display = 'block';

			} else if (data.autoJibunAddress) {
				var expJibunAddr = data.autoJibunAddress;
				guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				guideTextBox.style.display = 'block';
			} else {
				guideTextBox.innerHTML = '';
				guideTextBox.style.display = 'none';
			}
		}
	}).open();
}
/*function memRegi() {

	var fd = new FormData(document.forms.namedItem("signup"));
	fd.append('type', '1');


	fetch("/member/signup", {
		method: "POST",
		body: new URLSearchParams(fd)
	}).then(function(response) {
		return response.json();
	}).then(function(result) {
		if (result.status == "success") {
			location.href = "/junho/index.html";
		} else {
			window.alert("로그인 실패!")
		}
	});
	return false;
}
*/


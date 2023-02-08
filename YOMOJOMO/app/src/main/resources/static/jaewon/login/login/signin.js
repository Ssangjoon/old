// 카카오 자바스크립트 키 대입 
function kakaoInit() {
	Kakao.init('fa67ee5f663394562611310f849050dd')
}

// 페이스북 앱아이디, 버전 정보 기입
// 페이스북 sdk 설정 
function facebookInit() {
	window.fbAsyncInit = function() {
		FB.init({
			appId: '1646718509011068',
			cookie: true,
			xfbml: true,
			version: 'v13.0'
		});

		FB.AppEvents.logPageView();

	};

	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		console.log(js, fjs)
		if (d.getElementById(id)) { return; }
		js = d.createElement(s); js.id = id;
		js.src = "https://connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));


}
// 구글 sdk 설정
function googleInit() {
	console.log("init")
	// auth2 불러오기 
	gapi.load('auth2', function() {
		// sdk 설정
		gapi.auth2.init({
			client_id: '215152368438-vs1dn96k0c895p793shgq4nhfu7avg5h.apps.googleusercontent.com'
		}).then(function() {
			console.log("auth.init");
		});
	});
}
// 구글 로그인 함수
function snsGoogleLogin() {
	// 인증 요소 넣을 변수 생성
	var GoogleAuth = gapi.auth2.getAuthInstance();
	console.log("google.auth", GoogleAuth)
	// 구글 인증 요소를 넣고 결과값을 반환 받는다.
	GoogleAuth.signIn({
		scope: 'profile email'
	}).then(function() {
		//현재 유저 정보를 받는 변수를 생성한다.
		var googleUser = GoogleAuth.currentUser.get();
		console.log(googleUser)
		// 유저 프로필 정보를 담은 변수를 생성한다.
		var profile = googleUser.getBasicProfile();
		console.log(profile)
		console.log('Name: ' + profile.getName());
		console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
		// 필요한 요소를 호출해서 변수에 넣는다.
		var email = profile.getEmail();
		var name = profile.getName();
		// parameter에 넣을 값을 추출해서 append로 넣는다.
		var params = new URLSearchParams();
		params.append("email", email);

		// 백엔드 컨트롤러에 해당 파라미터를 넣어 이메일 중복체크를 한다.		
		fetch("/member/googleLogin", {
			method: "POST",
			body: params
		}).then(function(response) {
			return response.json();
		}).then(function(result) {
			console.log(result)
			if (result == 0) {
				alert("이미 탈퇴한 회원입니다.")
				return false
			}
			// 반환 값을 받고 분기를 나눈다.
			if (result.status == "success") {
				// data에 Y가 속했다면
				if (result.data == "Y") {
					alert("기존 회원")
					location.href = "/junho/index.html"
					// Y가 아닌 다른 data의 경우
				} else {
					alert("신규 회원")
					var expire = new Date();
					// JSON 형태 데이터 생성
					var snsLoginData = {
						email: email,
						name: name,
						type: 'google'
					}
					// 만료시간 설정 변수선언
					expire.setSeconds(expire.getSeconds() + 30);

					// Cookies에 문자열 형태로 데이터를 저장한다.
					Cookies.set('snsLoginData', JSON.stringify(snsLoginData), { expires: expire })

					location.href = "/jaewon/signup/index.html"

				}

			} else {
				window.alert("구글 로그인 실패!");
			}
		})
	}).catch(function() {
		alert("구글 로그인 실패")
	})

}

function snsKakaoLogin() {
	// 로그인 기능 접근
	Kakao.Auth.login({
		// 제대로 로그인 정보를 입력하고 들어가면 함수를 실행한다
		success: function(authObj) {
			//Access 토큰을 부여받는다.
			console.log(authObj)
			// 부여받은 토큰을 해당 URL로 보내면 결과값을 반환한다.
			Kakao.API.request({
				url: '/v2/user/me',
				success: function(response) {
					console.log(response);
					console.log(response.kakao_account);
					console.log(response.kakao_account.email);
					console.log(response.kakao_account.profile.nickname);
					// 반환 값 중에 내가 필요한 요소를 받아 변수에 저장한다.
					var email = response.kakao_account.email
					var name = response.kakao_account.profile.nickname
					// 파라미터에 넣을 값을 넣는다.
					var params = new URLSearchParams();
					params.append("email", response.kakao_account.email);

					// 요소 중복체크 
					fetch("/member/kakaoLogin", {
						method: "POST",
						body: params
					}).then(function(response) {
						return response.json();
					}).then(function(result) {
						console.log(result)
						if (result == 0) {
							alert("이미 탈퇴한 회원입니다.")
							return false
						}
						// 반환 값이 석세스일때
						if (result.status == "success") {

							if (result.data == "Y") {
								alert("기존 회원")
								location.href = "/junho/index.html"
							} else {
								alert("신규 회원")
								var expire = new Date();
								var snsLoginData = {
									email: email,
									name: name,
									type: 'kakao'
								}
								expire.setSeconds(expire.getSeconds() + 30);


								Cookies.set('snsLoginData', JSON.stringify(snsLoginData), { expires: expire })
								//JSON.parse(Cookies.get('name2'))
								location.href = "/jaewon/signup/index.html"

							}

						} else {
							window.alert("카카오 로그인 실패!");
						}
					})
				},
				fail: function(error) {
					console.log(error);
				}
			});
		},
		fail: function() {
			alert("카카오 로그인 실패")
		},
	})

}

function snsFacebookLogin() {
	FB.login(function(response) {
		//반환값을 확인한다.
		if (response.status === 'connected') { // 로그인이 정상적으로 되었을 때,
			// 반환받은 액세스 토큰을 파라미터로 보낸다.
			var params = new URLSearchParams();
			params.append("accessToken", response.authResponse.accessToken);
			//중복체크
			fetch("/member/facebookLogin", {
				method: "POST",
				body: params
			}).then(function(response) {
				return response.json();
			}).then(function(result) {
				if (result == 0) {
					alert("이미 탈퇴한 회원입니다.")
					return false
				}
				// 값을 반환 받는다.
				if (result.status == "success") {

					if (result.data == "Y") {
						alert("기존 회원")
						location.href = "/junho/index.html"
					} else {
						alert("신규 회원")
						//반환받은 데이터를 split하여 요소값을 넣는다.
						var data = result.data.split("____")

						var expire = new Date();
						var snsLoginData = {
							email: data[0],
							name: data[1],
							type: 'fb'
						}
						expire.setSeconds(expire.getSeconds() + 30);

						Cookies.set('snsLoginData', JSON.stringify(snsLoginData), { expires: expire })
						//JSON.parse(Cookies.get('name2'))
						location.href = "/jaewon/signup/index.html"

					}

				} else {
					window.alert("페이스북 로그인 실패!");
				}
			})

		} else { // 로그인이 되지 않았을 때,
			console.log("로그인 되지 않았음");
		}
	});
}
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
				history.go(-1);
			} else {
				window.alert("등록되지 않거나 탈퇴한 사용자입니다.")
			}
		});
		return false;
	};
}





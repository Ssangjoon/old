
  $.ajax({ //로그인 회원 정보 가져오기
    url: "/member/getLoginUser",
    type: "POST",
    datatype: "json",
    success: function(result) {
      const memberNo = result.data.no;
      if (result.status == "fail") {
        return false
      }
      window.loginUser = result.data;
    }
  })

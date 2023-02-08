function list() {

  // 1) URL에서 쿼리스트링(query string)을 추출한다.
  var arr = location.href.split("?");
  console.log(arr);

  if (arr.length == 1) {
    alert("요청 형식이 옳바르지 않습니다.")
    throw "URL 형식 오류!";
  }

  var qs = arr[1];
  console.log(qs);

  // 2) 쿼리 스트링에서 email 값을 추출한다.
  var params = new URLSearchParams(qs);
  var no = params.get("no");

  if (no == null) {
    alert("해당 번호의 연락처가 없습니다.");
    throw "파라미터 오류!";
  }
  console.log(no);

  // 3) 서버에서 데이터 가져오기
  var inbox = document.querySelector(".inbox")
  var divget = document.querySelector("#div-get");
  var htmlGenerator = Handlebars.compile(divget.innerHTML);

  fetch(`/noticeQuestion/select?no=${no}`).then(function(response) {
    return response.json();
  }).then(function(lists) {

    inbox.innerHTML = htmlGenerator(lists);
    console.log(lists)

  })
}

$(document).on('click', '.update-btn', function() {
  var xNo = document.querySelector(".update-btn").value;
  location.href = "/jaewon/admin/qna/view/uindex.html?no=" + xNo;
})

$(document).on('click', '.reply-btn', function(e) {
  e.preventDefault();
  var xNo = document.querySelector(".reply-btn").value;
  var xAnswer = document.querySelector("input[name=reply]").value;
  if (xAnswer===''){
    alert("빈 값을 넣을 수 없습니다.")
    return false;
  }
  
  fd = new FormData();
  fd.append("no", xNo)
  fd.append("answer", xAnswer)
  fetch("/noticeQuestion/updateAnswer", {
    method: "POST",
    body: new URLSearchParams(fd)
  }).then(function(response) {
    return response.json();
  }).then(function(result) {
    if (result.status == "success") {
      location.href = "/jaewon/admin/qna/view/index.html?no="+ xNo;
    } else {
      window.alert("댓글 등록을 실패하였습니다.")
    }
  });
  return false;
})

$(document).on('click', '.reset-btn', function(e) {
  e.preventDefault();
  var xNo = document.querySelector(".reply-btn").value;
  var xAnswer = document.querySelector("input[name=reply]").value;
  
  fd = new FormData();
  fd.append("no", xNo)
  fd.append("answer", "운영자의 답변을 기다리는 중입니다.")
  fetch("/noticeQuestion/updateAnswer", {
    method: "POST",
    body: new URLSearchParams(fd)
  }).then(function(response) {
    return response.json();
  }).then(function(result) {
    if (result.status == "success") {
      location.href = "/jaewon/admin/qna/view/index.html?no="+ xNo;
    } else {
      window.alert("댓글 등록을 실패하였습니다.")
    }
  });
  return false;
})

$(document).on('click', '.canceled-btn', function() {
  e.preventDefault();
  console.log("1")
  //location.href = "/jaewon/admin/qna/index.html?no=1&cutno=5&searchKeyword=";
})

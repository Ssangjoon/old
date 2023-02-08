$(document).on('click', '.create-btn', function() {
	var xTitle = document.querySelector("input[name=title]");
	var xContent = document.querySelector("textarea[name=content]");
	var xQcate = document.querySelector(".qcate");
	var xTV = xTitle.value
	var xQC = xQcate.value;
  var xCT = xContent.value
  
if(xTV === '' || xQC === '' || xCT === ''){
  alert("빈 항목이 있는 상태로 작성할 수 없습니다.")
  return false
}
  
	var fd = new FormData(); 
	fd.append("title", xTV);
	fd.append("maincateno", 2);
	fd.append("queryno", xQC);
	fd.append("content", xCT);

	fetch("/faq/add", {
		method: "POST",
		body: new URLSearchParams(fd)
	})
		.then(function(response) {
			return response.json();
		})
		.then(function(result) {
			if (result.status == "success") {
				alert("글 작성이 완료되었습니다.")
				location.href = "/jaewon/admin/faq/index.html?no=1&cutno=5&searchKeyword=";
			} else {
				window.alert("로그인 후 다시 시도해주세요");
			}
		});

	return false;
})

$(document).on('click', '.cancel-btn', function() {
	location.href = "/jaewon/admin/faq/index.html?no=1&cutno=5&searchKeyword=";
})

$(document).on('click', '.cancel-finish-btn', function() {
	var xNo = document.querySelector(".cancel-finish-btn").value;
	location.href = "/jaewon/admin/faq/view/index.html?no=" + xNo;
})

function queryCate() {
  var qCatelist = document.querySelector(".qcate");
  var SelectQuery = document.querySelector("#select-query");
  var htmlGeneratorqc = Handlebars.compile(SelectQuery.innerHTML);
  fetch("/queryCate/list")
    .then(function(response) {
      return response.json();
    }).then(function(result) {
      if (result.status == "fail") {
        window.alert("서버 요청 오류!");
        console.log(result.data);
        return;
      }
      qCatelist.innerHTML = htmlGeneratorqc(result);
    });
}
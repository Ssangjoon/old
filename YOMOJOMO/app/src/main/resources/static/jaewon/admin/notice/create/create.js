$(document).on('click', '.create-btn', function() {
	var xTitle = document.querySelector("input[name=title]");
	var xContent = document.querySelector("textarea[name=content]");
	
	var xTV = xTitle.value
	var xCT = xContent.value
	
  if(xTV === '' || xCT === ''){
  alert("빈 항목이 있는 상태로 작성할 수 없습니다.")
  return false
}
  
  var fd = new FormData(); 
	fd.append("title", xTV);
	fd.append("mainCategoryNo", 1);
	fd.append("cate", 1);
	fd.append("content", xCT);

	fetch("/notice/add", {
		method: "POST",
		body: new URLSearchParams(fd)
	})
		.then(function(response) {
			return response.json();
		})
		.then(function(result) {
			if (result.status == "success") {
				alert("글 작성이 완료되었습니다.")
				location.href = "/jaewon/admin/notice/index.html?no=1&cutno=5&searchKeyword=";
			} else {
				window.alert("로그인 후 다시 시도해주세요");
			}
		});

	return false;
})

$(document).on('click', '.cancel-btn', function() {
	location.href = "/jaewon/admin/notice/index.html?no=1&cutno=5&searchKeyword=";
})

$(document).on('click', '.cancel-finish-btn', function() {
	var xNo = document.querySelector(".cancel-finish-btn").value;
	location.href = "/jaewon/admin/notice/view/index.html?no=" + xNo;
})
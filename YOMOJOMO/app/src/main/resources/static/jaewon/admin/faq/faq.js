function pagelist() {
	// 1) URL에서 쿼리스트링(query string)을 추출한다.
	var arr = location.href.split("?");
	console.log(arr);

	var qs = arr[1];
	console.log(qs);

	// 2) 쿼리 스트링에서 email 값을 추출한다.
	var params = new URLSearchParams(qs);
	var no = params.get("no");
	var pageNo = no;
	var cutno = params.get("cutno");
	var cutCount = cutno;

	var searchKeyword = params.get("searchKeyword");

	if (no == null) {
		alert("해당 페이지는 없습니다.");
		throw "파라미터 오류!";
	}
	console.log(no);

	// 3) 서버에서 데이터 가져오기
	var tbody = document.querySelector("tbody")
	var content = document.querySelector("#content");
	var htmlGenerator = Handlebars.compile(content.innerHTML);
	fetch(`/faq/listselect?no=${pageNo}&cutno=${cutCount}&searchKeyword=${searchKeyword}`)
		.then(function(response) {
			return response.json();
		}).then(function(lists) {
			console.log(lists)
			tbody.innerHTML = htmlGenerator(lists);
		});

	fetch(`/faq/faqcountselect?searchKeyword=${searchKeyword}`)
		.then(function(response) {
			return response.json();
		}).then(function(lists) {
			allBtn(lists);
		});

	$(document).on('click', 'a', function(e) {
		no = e.target.dataset.num
		passNo = no;
		location.href = "/jaewon/admin/faq/index.html?no=" + passNo + "&cutno=" + cutno + "&searchKeyword=" + searchKeyword;
	})

	//}

};

$(document).on('click', '.searchBtn', function() {
	var xSearchName = document.querySelector("input[name=searchName]")
	console.log(xSearchName)
	console.log(xSearchName.value)

	if (xSearchName.value == "" || xSearchName.value == "/\s/g") {
		window.alert("빈 검색어 혹은 공백이 있는 검색어입니다.");
		return false;
	}
	// 1) URL에서 쿼리스트링(query string)을 추출한다.
	var arr = location.href.split("?");
	console.log(arr);

	var qs = arr[1];
	console.log(qs);
	var params = new URLSearchParams(qs);
	var cutno = params.get("cutno");
	var searchKeyword = xSearchName.value;
	location.href = "/jaewon/admin/faq/index.html?no=1" + "&cutno=" + cutno + "&searchKeyword=" + searchKeyword;
})



function allBtn(lists) {

	console.log("lists::::", lists)
	// 1) URL에서 쿼리스트링(query string)을 추출한다.
	var arr = location.href.split("?");
	console.log(arr);

	var qs = arr[1];
	console.log(qs);

	// 2) 쿼리 스트링에서 email 값을 추출한다.
	var params = new URLSearchParams(qs);
	var no = params.get("no");
	var pageNo = no;
	var cutno = params.get("cutno");
	var cutCount = cutno;


	if (no == null) {
		alert("해당 페이지는 없습니다.");
		throw "파라미터 오류!";
	}
	console.log(no);

	var pageSize = cutno; //한 페이지에 표출할 로우 수
	var pageCount = 3; // 한 페이지에 표출할 목록 수 
	var totalContent = lists
	console.log(totalContent)

	if (totalContent === 0) {
		return false;
	}

	var totalPageSize = Math.ceil(totalContent / pageSize)
	console.log(totalPageSize)

	var pageGroup = Math.ceil(pageNo / pageCount)
	console.log(pageGroup)
	var last = pageGroup * pageCount;
	console.log("last::::", last)
	if (last >= totalPageSize) {
		last = totalPageSize;
	}

	console.log("last::::", last)
	let first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호
	let next = last + 1;
	let prev = first - 1;

	if (first <= 0) {
		first = 1;
	}
	console.log("first::::", first)
	var botn = document.querySelector(".botn");
	var prbtn = document.querySelector(".prbtn");
	var nebtn = document.querySelector(".nebtn");
	var pagebtn = document.querySelector("#page-btn");
	var htmlGeneratorpa = Handlebars.compile(pagebtn.innerHTML);
	var prevPageBtn = document.querySelector("#prevpage-btn");
	var htmlGeneratorpr = Handlebars.compile(prevPageBtn.innerHTML);
	var nextPageBtn = document.querySelector("#nextpage-btn");
	var htmlGeneratorne = Handlebars.compile(nextPageBtn.innerHTML);

	var prevobj = new Object();
	prevobj.num = prev;
	console.log(prevobj)
	var btnlist = new Array()
	var nextobj = new Object();
	nextobj.num = next;
	console.log(nextobj)

	for (i = first; i <= last; i++) {
		var obj = new Object();
		obj.num = i;
		console.log(obj)
		btnlist.push(obj)
		console.log(btnlist)
	}

	if (prev > 0) {
		prbtn.innerHTML = htmlGeneratorpr(prevobj);
	}

	botn.innerHTML = htmlGeneratorpa(btnlist);

	if (next <= totalPageSize) {
		nebtn.innerHTML = htmlGeneratorne(nextobj);
	}


	var selectPage = document.querySelector("" + '.' + "p" + pageNo + "");

	for (var i = 0; i < pagebtn.length; i++) {
		pagebtn[i].classList.remove("active");
	}

	selectPage.classList.add("active");
}

$(document).on('click', '.write', function() {
  location.href="/jaewon/admin/faq/create/index.html"
  })

$(document).ready(function () {
  $('#header').load('/junho/mainHeader.html'); //헤더 인클루드
  $('#footer').load('/junho/mainfooter.html'); //푸터부분 인클루드
});
// 파마미터 값 얻어오기
var qs = window.location.search
console.log(qs)
var params = new URLSearchParams(qs);
gnoParameter = params.get('gno');
calNoParameter = params.get('cal_no');
console.log(params.get('gno'))
console.log(params.get('cal_no'))

// Member 정보 담을 배열
var memberList = [];
selesctData();
// 전체선택 함수
function selectAll(selectAll)  {
  const checkboxes 
       = document.getElementsByName('member');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}

// 검색어 함수
function searchMember() {
  // var groupListItems = document.querySelectorAll('.list-group-item');
  // groupListItems.forEach((item) => {
  //   item.classList.add('list-search')
  // })
  var searchValue = document.querySelector('.invite .search input').value.toUpperCase();
  console.log(searchValue)

  var name;
  var items = document.querySelectorAll('.list-group-item-search');
  console.log(items)
  for(var i=0;i<items.length;i++){
    name = items[i].getElementsByClassName("member-name");
    console.log(name)
    if(name[0].innerHTML.toUpperCase().indexOf(searchValue) > -1){
      items[i].style.display = "flex";
    }else{
      items[i].style.display = "none";
    }
  }
}
var ulEL = document.querySelector(".list-group")
    
// 템플릿 엔진에서 사용할 HTML 조각을 가져오기
var liTemplate = document.querySelector("#li-template");
console.log(liTemplate)
//템플릿 엔진 준비
var htmlGenerator = Handlebars.compile(liTemplate.innerHTML);

var listFirstStr = `<li class="list-group-item">
<input class="form-check-input me-1" type="checkbox" value="selectAll" aria-label="..." name="member" onclick="selectAll(this)">
<span style="padding: 5px;">전체선택</span>
</li>`



function selesctData() {
  // var data = {"group.no" : 1};
  
  $.ajax({
    url: `/midpoint/member/list?group.no=${gnoParameter}`,
    // data: data,
    type: "POST",
    dataType: "json",
    success : function(result) {
      for(var i = 0; i < result.length; i++) {
        memberList.push(result[i].member)
      }
      listFirstStr += htmlGenerator(memberList)
      ulEL.innerHTML =listFirstStr;
    }      
  })
  
}



function sendLinkCustom() {
  Kakao.init("2b7fdb2b98c4e61592bd51f09cac1ca8");
  Kakao.Link.sendCustom({
      templateId: 75255 ,
      templateArgs: {'url' : `/junho/midpoint/membermidpoint.html?gno=${gnoParameter}&cal_no=${calNoParameter}`}
  });
}

$('.btn-gomidpoint').click(function() {
  window.location.href = `/junho/midpoint/membermidpoint.html?gno=${gnoParameter}&cal_no=${calNoParameter}`
})




























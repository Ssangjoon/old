import{getGroupNO,getLoginUser,findgrouplistByGno} from '/ssang/js/module.js';
var getGroup = getGroupNO();//쿼리스트링에서 가져온 모임번호 
var loginUser = await getLoginUser() //로그인한 유저의 정보
var groupList = await findgrouplistByGno(getGroup) // 그룹번호로 조회한 모임과 회원정보 
// console.log(groupList.data)
// console.log(loginUser.data.no)
const PATH = {
  'groupList' : '/group/list',
  'groupGet' : '/group/get',
  'applyQuestion' : '/applyQuestion/findQuestion'
}

// 사진
fetch(`/group/get?gno=${getGroup}`)
  .then(function(response){
  return response.json()
  })
  .then(function(result){
    console.log(result.data)
    $('#group-title').text(result.data.groupName)
    $('.group-intro').text(`인사말 : ${result.data.intro}`)
    $('.group-memb-cnt').text(`회원수 : ${result.data.memberCount}/${result.data.maxCount}명`)
    if (result.status == "fail") {
      window.alert("서버 요청 오류!");
      console.log(result.data);
      return;
    }
    if(result.data.logo == null){
      result.data.logo = "default.png";
    }
    var path2 = "/group/photo?filename=" + result.data.logo
    document.querySelector('.main-logo').setAttribute("src", path2)
  });
  

  // 질문목록
  fetch(`/applyQuestion/findQuestion?no=${getGroup}`)
    .then(function(res) {
      return res.json();
    })
    .then(function(result) {
      console.log(result.data)
      if (result.status == "fail") {
        window.alert("서버 요청 오류!");
        return;
      }
      
      // 핸들바
      var writtenContainer = document.querySelector("#handlebars-container");
      var divTemplate = document.querySelector("#applyList-template");
      var htmlGenerator = Handlebars.compile(divTemplate.innerHTML);
      writtenContainer.innerHTML = htmlGenerator(result.data);
      $('#apply').val(getGroup);
    });

// 신청하기
$(document).on("click","#apply",function(){
  var value = $(this).val(); //모임번호
  var answerLength = $("input[name=answer]").length
  if (answerLength >= 1) {
    console.log("test")
  var qs = "";
  for(var i=0; i<answerLength; i++){                          
    var answer = $("input[name=answer]").eq(i).val()
    var qno = $("input[name=answer]").eq(i).data('qno')
    qs += `answers=${qno}_${answer}&`;
  }
  console.log(qs)
  fetch(`/applyAnswer/add?${qs}`)
  .then(function(res){
    return res.json()
  }).then(function(result){
  });
  }
  var defaultValue = document.querySelector("textarea[name=answer]").value
  var qs2 = `groupNo=${value}&content=`+encodeURI(defaultValue)//특수문자 인코딩
  console.log(qs2)
  fetch(`/applyFixedAnswer/add?${qs2}`)
  .then(function(res){
    return res.json()
  })
  .then(function(result){
    if(result.status == "success"){
    location.href="/mypage/group.html"
    }
    else{
      alert("이미 신청한 모임입니다.")
      location.replace("/junho/index.html")
    }
  })
})


$(document).on("click",".board-edit",function(){
  window.location.href = "view.html";
})
$(document).on("click",".board-edit",function(){
  window.location.href = "view.html";
})



// 버튼클릭이벤트 
document.querySelector(".new-post-btn").onclick = function() {
  window.location.href = "form.html";
};


//------------------------------------------------------------------------
// 가입신청 보내기

// 파마미터 값 얻어오기
// var qs = window.location.search;
// var params = new URLSearchParams(qs);
// console.log(params.get('gno'));
// var gNoParameter = params.get('gno');

// // 가입 신청하는 모임장 알아 오기
// var masterNo = 0;
// function getMaster() {
//   var data = {'joinMember.group.no': gNoParameter}
//   $.ajax({
//     url: '/group/getmasteruser',
//     type: 'POST',
//     dataType: 'json',
//     data:data,
//     success: function(result) {
//       masterNo = result
//     }
//   })
// }
// getMaster()

// var loginUserNo = loginUser.data.no

// console.log(loginUserNo)



import{getGroupNO,qCount} from '/ssang/js/module.js';
var groupNo = getGroupNO(); 
var questionCnt = await qCount(groupNo);
var qCnt = questionCnt.data
//신청서 등록 현황 
var br = document.querySelector("#accordion-body")
var brTemplate = document.querySelector("#br-template");
var htmlGenerator = Handlebars.compile(brTemplate.innerHTML);
fetch(`/applyQuestion/findQuestion?no=${groupNo}`)
  .then(function(res){
    return res.json()
  }).then(function(result){
    if (result.data.length != 0){
      br.innerHTML = htmlGenerator(result.data);
    }
  })
// 질문목록 추가 이벤트
var xQuestionList = document.querySelector('.x-question');
var xQuestionListCon = document.querySelector('#x-question-container')
document.querySelector("#x-addQuestion-btn").onclick = function() {
if (xQuestionListCon.childElementCount < 5) {
var questionList = xQuestionList.cloneNode(true);
questionList.querySelector("#listQuestionName").value = "";
xQuestionListCon.append(questionList);
return;
} 
Swal.fire({
  icon: 'error',
  title: '잠깐만요',
  text: '질문은 5개 까지만 올려주세요',
  footer: '',
})
}

// 멤버 신고 
var xTitle = document.querySelector('#recipient-name');
var xContent =document.querySelector('#message-text');
var value = $(this).val();
console.log(value)
$(document).on("click", "#group-report-exactly", function() {
  console.log('클릭')
  if(xTitle.value == "" || xContent.value == ""){
    alert("신고사유를 작성해주세요");
    return;
  }
  var fd = new FormData(document.forms.namedItem("form1"));
  fd.append('reported',groupNo)
  fd.append('rptCateNo',2)
  fetch("/report/add",{
    method : "POST",
    body : fd
  }).then(function(res){
    return res.json();
  }).then(function(result){
      if(result.status == 'success'){
    alert("신고가 완료되었습니다.")
    location.reload();
      }
  })
})///멤버 신고 끝
// 신청서 등록
var xQuestionCon = document.querySelector("#x-question-container")
document.querySelector("#x-apply-form").onclick = function() {
  var xQuestions = xQuestionCon.querySelectorAll(".x-question");
  if(xQuestions.length+qCnt < 6){ //질문 갯수 조건
  var qs="";
  for (var xQuestion of xQuestions) {
      var question = xQuestion.querySelector("input");
      qs += `&questionName=${question.value}`
  }
  fetch(`/applyQuestion/add?no=${groupNo}${qs}`)
    .then(function(res){
      return res.json()
    }).then(function(result){
      location.href=`/ssang/groupEdit/index.html?gno=${groupNo}`
    })
  } else{
    Swal.fire({
      icon: 'error',
      title: '잠시만요',
      text: '질문은 총 5개만 등록 가능합니다. ',
      footer: ''
    })
  }
}

// 신청서 수정
$(document).on("click", "#x-reform", function() {
var contents = document.querySelectorAll("input.apply-content")
var qs=""
for (var content of contents){
  var qno = content.getAttribute("data-qno")
  var value = content.value
  qs += `questions=${qno}_${value}&`
}
console.log(qs)
fetch(`/applyQuestion/update?${qs}`)
.then(function(res){
  return res.json()
}).then(function(result){
  console.log(result.data)
  Swal.fire({
    position: 'top-end',
    icon: 'success',
    title: '저장되었습니다',
    showConfirmButton: false,
    timer: 1500
  })
})
})


// var cnt = 0;
// setTimeout(function() {
//   cnt = document.querySelectorAll('.apply-content').length
//   console.log(cnt)
// },2000 )


// ---------------------------------------------------------
// 모임삭제버튼
var qs = window.location.search
console.log(qs)
var params = new URLSearchParams(qs);
var gNoParameter = params.get('gno');
console.log(gNoParameter)

function deleteGroup() {
  var data = {'no': gNoParameter};
  $.ajax({
    url: '/group/delete',
    type: 'POST',
    dataType: 'json',
    data: data,
    success: function(result) {
      console.log(result)
    }
  })
}

document.querySelector('#groupDelete').addEventListener('click', function() {
  deleteGroup();
  window.location.href = '/junho/index.html'
})

// ---------------------------------------------------------
// 모임탈퇴 버튼
var memberNo = 0;
function getLoginUser() {
  $.ajax({
    url: '/member/getLoginUser',
    type: 'POST',
    dataType: 'json',
    success: function(result) {
      memberNo= result.data.no
    }
  })
}
getLoginUser()

function deleteMember() {
  var data = {'member.no': memberNo, 'group.no': gNoParameter}
  $.ajax({
    url: '/joinmember/deleteMember',
    type: 'POST',
    dataType: 'json',
    data: data,
    success: function(result) {
      console.log(result)
    }
  })
}

document.querySelector('#button-addon2').addEventListener('click', function() {
  deleteMember();
  window.location.href = '/junho/index.html';
})



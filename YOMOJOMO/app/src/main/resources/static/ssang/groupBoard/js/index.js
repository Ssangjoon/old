import{getGroupNO,getLoginUser,findgrouplistByGno,findgrouplistByMno} from '/ssang/js/module.js';
var getGroup = getGroupNO();//쿼리스트링에서 가져온 모임번호 
var loginUser = await getLoginUser() //로그인한 유저의 정보
var groupList = await findgrouplistByGno(getGroup) // 그룹번호로 조회한 모임과 회원정보 
var getGrade= await findgrouplistByMno(loginUser.data.no) // 로그인 유저 번호로 모임멤버 조회
console.log(groupList.data)
console.log(loginUser.data)
var arr = [];
for (var list of groupList.data){
  arr.push(list.member.no) 
}
if(arr.includes(loginUser.data.no) == false){ 
  // 모임에 가입된 유저가 아니라면 메인화면으로 이동시킨다
  window.alert("가입한 모임만 입장 가능합니다")
  location.replace("/junho/index.html")
}
var MemberInfo = getGrade.data;
var memberAuthority; //멤버등급번호

for (var i=0; i < MemberInfo.length; i++) { //현재 있는 그룹의 정보 배열로 빼기
if (getGroup == MemberInfo[i].group.no) {
  memberAuthority = MemberInfo[i].memberGrade.gradeNo
}   
}
var writtenContainer = document.querySelector("#handlebars-container");
var divTemplate = document.querySelector("#div-template");
var htmlGenerator = Handlebars.compile(divTemplate.innerHTML);
// 로그인 정보 요청
fetch("/member/getLoginUser")
.then(function(res){
  return res.json()
})
.then(function(res) {
// 게시글 읽어오기
fetch(`/board/findByGroupNo?no=${getGroup}`)
.then(function(response) {
  return response.json();
})
.then(function(boards){
  for (var board of boards) {
    if (board.writer.no == loginUser.data.no) {//게시글 작성자 인지 검사
      board.isWriter = true;
    } else {
      board.isWriter = false;
    }
    if(memberAuthority == '1'){ // 모임장인지 검사
      board.isMaster = true;
    } else {
      board.isMaster = false;
    }

  }
  console.log(boards);
  writtenContainer.innerHTML = htmlGenerator({
    list : boards,
    user : loginUser.data
  });
})
})// fetch -> member/getLoginUser

//오래된 순 
$(".select-orderby").on("click", function() {
  var value =  $(".select-orderby option:selected").val()
  if (value == 1){
    fetch(`/board/findByGroupNo?no=${getGroup}`)
    .then(function(response) {
      return response.json();
    })
    .then(function(boards){
      for (var board of boards) {
        if (board.writer.no == loginUser.data.no) {//게시글 작성자 인지 검사
          board.isWriter = true;
        } else {
          board.isWriter = false;
        }
        if(memberAuthority == '1'){ // 모임장인지 검사
          board.isMaster = true;
        } else {
          board.isMaster = false;
        }

      }
      console.log(boards);
      writtenContainer.innerHTML = htmlGenerator({
        list : boards,
        user : loginUser
      });
    })
  } else if(value ==2){
    fetch(`/board/findByGroupNoAsc?no=${getGroup}`)
    .then(function(response) {
      return response.json();
    })
    .then(function(result){
      var boards = result.data 
      for (var board of boards) {
        if (board.writer.no == loginUser.data.no) {//게시글 작성자 인지 검사
          board.isWriter = true;
        } else {
          board.isWriter = false;
        }
        if(memberAuthority == '1'){ // 모임장인지 검사
          board.isMaster = true;
        } else {
          board.isMaster = false;
        }

      }
      console.log(boards);
      writtenContainer.innerHTML = htmlGenerator({
        list : boards,
        user : loginUser
      });
    })
  }
})

// 게시글 신고 
var xTitle = document.querySelector('#recipient-name');
var xContent =document.querySelector('#message-text');
$(document).on("click", ".board-report", function() {
  var value = $(this).val();
  console.log(value)
  $(document).on("click", "#board-report-exactly", function() {
    console.log('클릭')
    if(xTitle.value == "" || xContent.value == ""){
      alert("신고사유를 작성해주세요");
      return;
    }
    var fd = new FormData(document.forms.namedItem("form1"));
    fd.append('reported',value)
    fd.append('rptCateNo',3)
    fetch("/report/add",{
      method : "POST",
      body : fd
    }).then(function(res){
      return res.json();
    }).then(function(result){
      alert("신고가 완료되었습니다.")
      location.reload();
    })
  })
})///게시글 신고 끝
// 댓글 등록
$(document).on("click", ".comment-btn", function() {
  var value = $(this).val()
  var input = $(`input[name=${value}]`).val()
  var fd = new FormData;
  fd.append('boardNo',value)
  fd.append('groupNo',getGroup)
  fd.append('content',input)
  fetch("/comment/add",{
  method:"post",
  body:fd
})
.then(res=>res.json())
.then(res=>{
  console.log(res.status)
  location.href=`index.html?gno=${getGroup}`
})
})
//삭제 
$(document).on('click','.board-delete',function(){
  var value = $(this).val();
  fetch(`/board/delete?no=${value}`)
  .then(function(res){
    return res.json()
  }).then(function(result){
    location.reload()
  })
})
//--버튼클릭이벤트 -->
$(document).on("click",".new-post-btn",function(){
  window.location.href = `form.html?gno=${getGroup}`;
});
$(document).on("click",".board-edit",function(){
  var value = $(this).val()
  window.location.href = `view-board-edit.html?gno=${getGroup}&bno=${value}`;
})
$(document).on("click",".written-content",function(){
  var value = $(this).data('bno')
  window.location.href = `view.html?gno=${getGroup}&bno=${value}`;
})
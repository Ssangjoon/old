getLoginUser()
const swa = Swal.mixin({
  customClass: {
    confirmButton: 'btn btn-primary mx-2',
    cancelButton: 'btn btn-secondary mx-2'
  },
  buttonsStyling: false
})

$(".card").children("img").click(function(e){
  location.href = "/ssang/groupBoard/index.html"
})
$(".g-title").click(function(e){
  $("tr").removeClass("lately")
  $(e.target.closest("tr")).addClass("lately")
  $(".modal").modal("show")
})
$(".btn-cancel").click(function(e){
  e.stopPropagation();
  swa.fire({
    title: '신청을 취소하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: '신청 취소',
    cancelButtonText: '취소',
  }).then((result) => {
    if (result.isConfirmed) {
      $(e.target).closest(".card").remove()
      swa.fire(
        '신청이 취소되었습니다',
        '',
        'success'
      )
    }
  })
})
$(".btn-accept").click(function(e){
  e.stopPropagation();
  swa.fire({
    title: '가입을 수락하시겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: '수락',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      $(".lately").remove()
      $(".modal").modal("hide")
      swa.fire(
        '가입을 수락하였습니다.',
        '',
        'success'
        )
      }
    })
  })
$(".btn-reject").click(function(e){
  e.stopPropagation();
  swa.fire({
    title: '가입을 거절하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: '거절',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      $(".lately").remove()
      $(".modal").modal("hide")
      swa.fire(
        '가입을 거절하였습니다.',
        '',
        'success'
        )
      }
    })
  })
  $(".all-check").click(function(e){
  if ($(".all-check").is(":checked")){
    $("input").prop("checked", true)
  } else {
    $("input").prop("checked", false)
  }
})
$(".btn-delete").click(function(e){
  if ($(".form-check-input").is(":checked") == false) {
    swa.fire(
    '글이 선택되지 않았습니다',
    '삭제할 글을 선택해주세요',
    'error'
    )} else {
      swa.fire({
      title: '글을 삭제 하시겠습니까?',
      text: '삭제하면 가입이 자동 거절됩니다',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: '삭제',
      cancelButtonText: '취소',
    }).then((result) => {
      if (result.isConfirmed) {
        $(".all-check").prop("checked", false)
        $("input:checkbox:checked").each(function(){
          this.closest("tr").remove()
        })
        swa.fire(
          '삭제가 완료되었습니다',
          '',
          'success'
          )
      }
    })
  }
})

var memberNo = 0;
function getLoginUser() {
  
  $.ajax({
    url: '/member/getLoginUser',
    type: "POST",
    dataType: 'json',
    success: function(result) {
      console.log(result)
      memberNo = result.data.no;
      console.log(memberNo)
      getSendList(memberNo)
      getReciveList(memberNo)
    }
  })
}

var divApplyTemplate = document.querySelector("#div-applyList-template");
console.log(divApplyTemplate)
//템플릿 엔진 준비
var htmlGeneratorApply = Handlebars.compile(divApplyTemplate.innerHTML);

var sendHandlebarsEl = document.querySelector('.sendHandlebars');
var sendGroupList=[]
function getSendList(i) {
  var data = {'sendMember.no': i}
  
  $.ajax({
    url: '/mypage/group/sendApplyList',
    type: "POST",
    dataType: 'json',
    data: data,
    success: function(result) {
      for(var i = 0; i < result.length; i++) {
        if (result[i].group.logo == null) {
          result[i].group.logo = "default.png";
        }
        console.log(result[i])
        sendGroupList.push(result[i].group)
      }
      console.log(sendGroupList)
      
      sendHandlebarsEl.innerHTML =htmlGeneratorApply(sendGroupList)
    }
  })
}

function sendDelete(i) {
  console.log('click')
  var data = {'sendMember.no': memberNo, 'group.no': sendGroupList[i].no};
  $.ajax({
    url: '/mypage/group/sendApplyDelet',
    type: "POST",
    dataType: 'json',
    data: data,
    success: function(result) {
      console.log(result);
      location.reload();
    }
  })
}
var reciveGroupList = [];
var trReciveTemplate = document.querySelector("#tr-reciveList-template");
console.log(trReciveTemplate)
//템플릿 엔진 준비
var htmlGeneratorRecive = Handlebars.compile(trReciveTemplate.innerHTML);
console.log()
var reciveHandlebarsEl = document.querySelector('#recivetest');
function getReciveList(i) {
  var data = {'reciveMember.no': i}
  
  $.ajax({
    url: '/mypage/group/reciveApplyList',
    type: "POST",
    dataType: 'json',
    data: data,
    success: function(result) {
      console.log(result)
      for (var i = 0; i <result.length; i++) {
        var temp = {'content': result[i].content,
         'groupName': result[i].group.groupName,
          'regDate':result[i].regdt,
          'sendMemberName': result[i].sendMember.memberName,
          'groupNo': result[i].group.no,
          'sendMemberNo':result[i].sendMember.no
        }
        reciveGroupList.push(temp)
      }
      console.log(reciveGroupList)
      reciveHandlebarsEl.innerHTML = htmlGeneratorRecive(reciveGroupList)
      console.log(htmlGeneratorRecive(reciveGroupList))
    }
  })
}

function joinGroup(i) {
  var data = {'sendMember.no': reciveGroupList[i].sendMemberNo, 'group.no': reciveGroupList[i].groupNo}
  $.ajax({
    url: '/mypage/group/joinGroup',
    type: "POST",
    dataType: 'json',
    data: data,
    success: function(result) {
      console.log(result)
      reciveDelete(i)
    }
  })
}

function reciveDelete(i) {
  console.log('click')
  var data = {'reciveMember.no': reciveGroupList[i].sendMemberNo, 'group.no': reciveGroupList[i].groupNo};
  $.ajax({
    url: '/mypage/group/reciveApplyDelete',
    type: "POST",
    dataType: 'json',
    data: data,
    success: function(result) {
      console.log(result);
      location.reload();
    }
  })
}












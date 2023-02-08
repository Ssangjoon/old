var arr = location.href.split("?");
if (arr.length == 1) {
  alert("요청 형식이 올바르지 않습니다.")
  throw "URL 형식 오류!";
}
var qs = arr[1];
var params = new URLSearchParams(qs);
var gno = params.get("gno");

$(document).ready(function() {
  $('#header').load('/junho/mainHeader.html');
  $('#top').load('/ssang/groupBoard/none-search-top.html');
  $('#footer').load('/junho/mainfooter.html');
  $(".List-Container").hide();
  
  $.ajax({ //로그인 여부 확인 ajax START 비회원은 main으로 보낸다.
      url : "/member/getLoginUser",
      type : "POST",
      datatype : "json",
      success : function(result) {
        let loginUserNo = result.data.no;
        if (result.status == "fail") {
          window.alert("회원이 아닙니다.");
          window.location.replace("/junho/index.html");
        } else {
          $.ajax({ //회원 등급 조회 모임장이 아니라면 등록 버튼을 감춘다.
            url : "/joinmember/grouplistbymno",
            type : "POST",
            datatype : "json",
            data : {"member.no" : loginUserNo },
            success : function(result) {
              MemberInfo = result.data;
              var memberAuthority; //멤버등급번호
              var currentGroup = []; //현재 모임정보

              for (i=0; i < MemberInfo.length; i++) { //현재 있는 그룹의 정보 배열로 빼기
              if (gno == MemberInfo[i].group.no) {
                // console.log("가입한 소모임이야");
                memberAuthority = MemberInfo[i].memberGrade.gradeNo
                currentGroup.push({
                  group : MemberInfo[i].group,
                  member : MemberInfo[i].member,
                  memberGrade : MemberInfo[i].memberGrade,
                })
              }       
            }

            if (currentGroup.length == 0 || gno != currentGroup[0].group.no  ) {
              window.alert("가입된 소모임이 아닙니다");
              location.href="/junho/index.html";
            } else {
              console.log("가입한 소모임");
            }

            if (memberAuthority != 1) {
              $("#x-add-btn").hide();
              $("#cleanMemb").hide();
              $("#selectMemb").hide();
            }//회원 등급 조회 if END 

            }//회원 등급 조회 success END           
          })//회원 등급 조회 ajax END 
        }//로그인 확인 else END
      }//로그인 여부 확인 success END
  }); //로그인 여부 확인 ajax END
});

let xTitle = document.querySelector("#x-title");
let xAmount = document.querySelector("#x-amount");
let xMembList = document.querySelector(".x-membList");
let xMembListCon = document.querySelector("#x-membList-container");

//멤버 전체 불러오기
let divTemplate = document.querySelector("#div-template");
let divGenerator = Handlebars.compile(divTemplate.innerHTML);

//모달창 내 멤버 전체 불러오기
let modalMemberList = document.querySelector("#modal-member-list");
let checkboxTemplate = document.querySelector("#modal-memberlist-template");
let checkboxGenerator = Handlebars.compile(checkboxTemplate.innerHTML);

//멤버 선택 버튼 클릭 -> 모달
$("#selectMemb").on("click", function() { //멤버리스트 버튼 클릭 EVENT START
  $("#memberlist-modal").modal("show");
  $.ajax({
    url : "/joinmember/grouplistbygno",
    type : "POST",
    data : {"group.no": gno}, 
    success: function(result) {
      let AllMember = result.data;
      modalMemberList.innerHTML = checkboxGenerator(result.data); 

      $("#selectedOK").on("click", function() { //모달창에서 적용하기 버튼 click event START
        let MembArr = []; //멤버번호랑, 이름만 있는 배열
        let checkedMembArr = []; //선택된 멤버 번호 배열
        let FinalChoicedMember = []; 
        console.log(FinalChoicedMember);

        for (i=0; i < AllMember.length; i++) { //멤버리스트 배열로 빼기
          // console.log(AllMember[i].member);
          MembArr.push(AllMember[i].member);
        }
        
        $("input:checkbox[name=memberNo]:checked").each(function() { //선택된 멤버 번호 배열로 빼기
          var checkedMemb = $(this).val();
          checkedMembArr.push(checkedMemb);
        })

        for (i=0; i<MembArr.length; i++) {
          for (j=0; j<checkedMembArr.length; j++) {
            if(MembArr[i].no == checkedMembArr[j]) {
              FinalChoicedMember.push({
                "no" : MembArr[i].no,
                "memberName" : MembArr[i].memberName,
              })
            }
          }
        } 

        $(".List-Container").show();
        xMembListCon.innerHTML = divGenerator(FinalChoicedMember); 
        $("#memberlist-modal").modal("hide");
        $("#x-membList-container").css({"border": "1px solid rgb(17, 147, 218)"})
        $("#memberlist-modal").modal("hide");
      })
      //모달창에서 적용하기 버튼 click event END

      //모달창 내 멤버 전체 버튼 클릭 EVENT START
      $("#allMemb").on("click", function() {
        $.ajax({
          url : "/joinmember/grouplistbygno",
          type : "POST",
          data : {"group.no": gno},
          success: function(result) {
            console.log(AllMember);
            let MembArr = []; //멤버번호랑, 이름만 있는 배열

            for (i=0; i < AllMember.length; i++) { //멤버리스트 배열로 빼기
              MembArr.push(AllMember[i].member);
            }

            $(".List-Container").show();
            xMembListCon.innerHTML = divGenerator(MembArr); 
            $("#memberlist-modal").modal("hide");
          } //success END
        })//ajax END
      })//모달창 내 멤버 전체 버튼 클릭 EVENT END
    } //success END
  })//ajax END
}) //멤버리스트 버튼 클릭 EVENT START END

//멤버 개별 div의 삭제 이벤트
function deleteDiv(e) {
  xMembListCon.removeChild(e.target.parentNode);

  //모든 멤버를 삭제 했을 때
let memberCount = xMembListCon.childElementCount;
  if (memberCount == 0) {
    $(".List-Container").hide();
  // $("#x-membList-container").empty();
  }
};

//멤버 초기화 버튼
$("#cleanMemb").on("click", function() {
  $(".List-Container").hide();
});

//등록 버튼 클릭
document.querySelector("#x-add-btn").onclick = function() {
  let memberCount = xMembListCon.childElementCount;
  let memberListAll = document.querySelectorAll(".x-membList");
  if(
    $("#x-selectCate option:selected").val() == 1 || 
     xTitle.value == "" || 
     xAmount.value == "" ||
     memberCount == 0) {
      window.alert("필수 입력 항목이 비어 있습니다.");
      return;
  }

  var qs = `group.no=${gno}&actCate.no=${$("#x-selectCate option:selected").val()}&title=${xTitle.value}&amount=${xAmount.value}&status=${$("#x-status option:selected").val()}`
    for (var memberDiv of memberListAll) {
      var xMembNo = memberDiv.querySelector("#membno");
      var xDate = memberDiv.querySelector("#paydt");
      if(xDate.value == "") {
        qs += `&qslength=2&actStatuses=${gno}_${xMembNo.value}`;
      } else {
        qs += `&qslength=3&actStatuses=${gno}_${xMembNo.value}_${xDate.value}`;
      }
    }

  fetch(`/accounting/add?${qs}`)
  .then(function(response) {
    return response.json();
  })
  .then(function(result) {
    console.log(result);
    if (result.status == "success") {
      window.alert("등록되었습니다.");
      location.href = "index.html?gno=" + gno;
    } else {
      window.alert("게시글 등록 실패!");
      console.log(result.data);
    }
  })
} //add btn event END

//취소
document.querySelector("#x-cancel-btn").onclick = function() {
  window.location.href = "index.html?gno=" + gno;
};
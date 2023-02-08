$(document).ready(function () {
  $('#header').load('/junho/mainHeader.html'); 
  $('#top').load('/ssang/groupBoard/none-search-top.html');
  $('#footer').load('/junho/mainfooter.html');

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
                // console.log(result.data);
                MemberInfo = result.data;
                var memberAuthority; //멤버등급번호
                var memberAuthority; //멤버등급번호
                var currentGroup = []; //현재 모임정보

                for (i=0; i < MemberInfo.length; i++) { //현재 있는 그룹의 정보 배열로 빼기
                if (AccountingGno == MemberInfo[i].group.no) {
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

                if (memberAuthority != 1) { //모임장이 아니라면
                  console.log("모임장아님");
                  $("#x-update-btn").hide();
                  $("#x-delete-btn").hide();
                  $("#newMemb").hide();
                  $("#cleanMemb").hide();
                  $("#recoverMemb").hide();
                  $(document.querySelectorAll("#x-deleteMemb-btn")).hide();
                  $(".inner > form > table > tbody > tr > td > input").attr("readonly", true);
                  $(".inner > form > table > tbody > tr > td > select").prop("disabled", true);
                  $(document.querySelectorAll("#listmembName")).attr("readonly", true);
                  $(document.querySelectorAll("#paydt")).attr("readonly", true);
                } else {
                  console.log("모임장이야");
                  $("#x-cancle-btn").text("취소");
                }//회원 등급 조회 if END
              }//회원 등급 조회 success END           
            })//회원 등급 조회 ajax END 
          }//로그인 확인 else END
        }//로그인 여부 확인 success END
  }); //로그인 여부 확인 ajax END

});

let qsarr = location.href.split("?");
splitQs = qsarr[1];
let AccountingParams = new URLSearchParams(splitQs);
let AccountingGno = AccountingParams.get("gno");
let AccountingNo = AccountingParams.get("no");

//파라미터가 없을 때
if (qsarr.length == 1) {
  alert("요청 형식이 올바르지 않습니다.")
  throw "URL 형식 오류!"
}

//게시글 번호가 없을 때
if (AccountingNo == null) {
  alert("게시물 번호가 없습니다.");
  throw "파라미터 오류!";
}

let xNo = document.querySelector("#x-no");
let xGno = document.querySelector("#x-gno");
let xActcateno = document.querySelector("#x-selectCate");
let xTitle = document.querySelector("#x-title");
let xAmount = document.querySelector("#x-amount");
let xDate = document.querySelector("#x-registDate");
let xStatus = document.querySelector("#x-status");
let xMembList = document.querySelector(".x-membList");
let xMembListCon = document.querySelector("#x-membList-container");
let divTemplate = document.querySelector("#div-template");
let divGenerator = Handlebars.compile(divTemplate.innerHTML);
let newDivTemplate = document.querySelector("#new-div-template");
let newDivGenerator = Handlebars.compile(newDivTemplate.innerHTML);
let modalMemberList = document.querySelector("#modal-member-list");
let checkboxTemplate = document.querySelector("#modal-memberlist-template");
let checkboxGenerator = Handlebars.compile(checkboxTemplate.innerHTML);
let plusDtMembArr = [];


//화면에 뿌림
$.ajax({
  url: "/accounting/get",
  type : "POST",
  data : {no : AccountingNo},
  success : function(result) {
    accountingResult = result.data
    xNo.value = accountingResult.accountingNo;
    xActcateno.value = accountingResult.actCateNo;
    xTitle.value = accountingResult.title;
    xAmount.value  = accountingResult.amount;
    xDate.value = accountingResult.registDate;
    xStatus.value = accountingResult.status;
    splitmemberNoArr = accountingResult.actStatus;

    //accounting의 멤버리스트
    var memberNoArr = [];
    $.each(splitmemberNoArr, function(index, splitmemberNoArr) {
      memberNoArr.push({
        membNo : splitmemberNoArr.membNo,
        paydt : splitmemberNoArr.paydt
      })//memberNoArr push END    
    })//each END

    console.log(splitmemberNoArr);

    //member의 멤버리스트
    $.ajax({
      url: "/member/list",
      type : "POST",
      success : function (result) {
        var memberArr = [];
      $.each()
        $.each(result, function(index, result) {
          memberArr.push({
            membNo : result.no,
            membName : result.memberName
          })// memberArr push END
        })//each END

        // console.log(MemberArr);

        let newMemberArr = [];
        for (i = 0; i < memberNoArr.length; i++) {
            let MemberArr = memberArr.filter(function(data) {
              return data.membNo == memberNoArr[i].membNo 
            })
            newMemberArr.push(MemberArr)
        }

        for (i = 0; i < memberNoArr.length; i++) {
          if(newMemberArr.flat()[i].membNo === memberNoArr[i].membNo ) {
            plusDtMembArr.push({
              memberNo : splitmemberNoArr[i].membNo,
              membName : newMemberArr.flat()[i].membName,
              paydt : splitmemberNoArr[i].paydt
            })
          }
        }
        xMembListCon.innerHTML = divGenerator(plusDtMembArr);

        //멤버 복구 click Event
        $("#recoverMemb").on("click", function() {
          $(".List-Container").show();
          xMembListCon.innerHTML = divGenerator(plusDtMembArr);
        }) //멤버 복구 clickEvent END

        console.log(plusDtMembArr);

        //멤버 변경 버튼 클릭 -> 모달
        $("#newMemb").on("click", function() { //멤버리스트 버튼 클릭 EVENT START
          $("#memberlist-modal").modal("show");
          $("#allMemb").show();
          $.ajax({
            url : "/joinmember/grouplistbygno",
            type : "POST",
            data : {"group.no": gno}, 
            success: function(result) {
              let AllMember = result.data;
              modalMemberList.innerHTML = checkboxGenerator(AllMember); 

              $("#selectedOK").on("click", function() { //모달창에서 적용하기 버튼 click event START
                let MembArr = []; //멤버번호랑, 이름만 있는 배열
                let checkedMembArr = []; //선택된 멤버 번호 배열
                let FinalChoicedMember = []; 
                

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
                xMembListCon.innerHTML = newDivGenerator(FinalChoicedMember);
                $("#memberlist-modal").modal("hide");
              }) //모달창에서 적용하기 버튼 click event END


              //모달창 내 멤버 전체 버튼 클릭 EVENT START
              $("#allMemb").on("click", function() {
                $.ajax({
                  url : "/joinmember/grouplistbygno",
                  type : "POST",
                  data : {"group.no": gno},
                  success: function(result) {
                    // console.log(AllMember);
                    let MembArr = []; //멤버번호랑, 이름만 있는 배열

                    for (i=0; i < AllMember.length; i++) { //멤버리스트 배열로 빼기
                      MembArr.push(AllMember[i].member);
                    }

                    $(".List-Container").show();
                    xMembListCon.innerHTML = newDivGenerator(MembArr); 
                    $("#memberlist-modal").modal("hide");
                  } //success END
                })//ajax END
              })//모달창 내 멤버 전체 버튼 클릭 EVENT END
            } //success END
          })//ajax END
        }) //멤버리스트 버튼 클릭 EVENT START END



      } //member의 멤버리스트 Success END
    }) //member의 멤버리스트 ajax END
  }// success END
});//ajax END

//삭제 
document.querySelector("#x-delete-btn").onclick = function() {
  fetch(`/accounting/delete?accountingNo=${AccountingNo}`)
  .then(function(response) {
      return response.json();
    })
  .then(function(result) {
    console.log("삭제");
    console.log(result);
	  location.href = "index.html?gno=" + AccountingGno;
  });
};

//취소
document.querySelector("#x-cancle-btn").onclick = function() {
  window.location.href = "index.html?gno=" + AccountingGno;
};

//수정
document.querySelector("#x-update-btn").onclick = function() {
  let memberCount = xMembListCon.childElementCount;
  let memberListAll = document.querySelectorAll(".x-membList");
  let newMemberList = document.querySelectorAll(".new-x-membList")
  if(
      $("#x-selectCate option:selected").val() == 1 || 
      xTitle.value == "" || 
      xAmount.value == "" ||
      memberCount == 0) {
        window.alert("필수 입력 항목이 비어 있습니다.");
        return;
    }

  //쿼리스트링 만들기
  
  var qs = `accountingNo=${xNo.value}&group.no=${gno}&actCate.no=${$("#x-selectCate option:selected").val()}&title=${xTitle.value}&amount=${xAmount.value}&status=${$("#x-status option:selected").val()}`
  
  if (memberListAll.length != 0) { //일반 수정 쿼리스트링
      for (var memberDiv of memberListAll) {
        var xMembNo = memberDiv.querySelector("#membno");
        // var xGno = memberDiv.querySelector("#gno");
        var xDate = memberDiv.querySelector("#paydt");
        if(xDate.value == "") {
          qs += `&qslength=2&actStatuses=${gno}_${xMembNo.value}`;
        } else {
          qs += `&qslength=3&actStatuses=${gno}_${xMembNo.value}_${xDate.value}`;
        }
      }
  } else { //멤버변경 수정 쿼리스트링
    for (var newMemberDiv of newMemberList) {
      var xMembNo = newMemberDiv.querySelector("#membno");
      var xDate = newMemberDiv.querySelector("#paydt");
      if(xDate.value == "") {
        qs += `&qslength=2&actStatuses=${gno}_${xMembNo.value}`;
      } else {
        qs += `&qslength=3&actStatuses=${gno}_${xMembNo.value}_${xDate.value}`;
      }
    }
  } //쿼리스트링 만드는 else문 끝

  fetch(`/accounting/update?${qs}`)
  .then(function(response) {
    return response.json();
  })
  .then(function(result) {
    console.log(result);
    if (result.status == "success") {
      window.alert("수정 되었습니다.");
      location.href = "index.html?gno=" + gno;
    } else {
      window.alert("게시글 등록 실패!");
      console.log(result.data);
    }
  }) //fetch END
};



// //멤버 변경 버튼 클릭 -> 모달
// $("#newMemb").on("click", function() { //멤버리스트 버튼 클릭 EVENT START
//   $("#memberlist-modal").modal("show");
//   $("#allMemb").show();
//   $.ajax({
//     url : "/joinmember/grouplistbygno",
//     type : "POST",
//     data : {"group.no": gno}, 
//     success: function(result) {
//       let AllMember = result.data;
//       modalMemberList.innerHTML = checkboxGenerator(AllMember); 

//       $("#selectedOK").on("click", function() { //모달창에서 적용하기 버튼 click event START
//         let MembArr = []; //멤버번호랑, 이름만 있는 배열
//         let checkedMembArr = []; //선택된 멤버 번호 배열
//         let FinalChoicedMember = []; 
        

//         for (i=0; i < AllMember.length; i++) { //멤버리스트 배열로 빼기
//           // console.log(AllMember[i].member);
//           MembArr.push(AllMember[i].member);
//         }
        
//         $("input:checkbox[name=memberNo]:checked").each(function() { //선택된 멤버 번호 배열로 빼기
//           var checkedMemb = $(this).val();
//           checkedMembArr.push(checkedMemb);
//         })

//         for (i=0; i<MembArr.length; i++) {
//           for (j=0; j<checkedMembArr.length; j++) {
//             if(MembArr[i].no == checkedMembArr[j]) {
//               FinalChoicedMember.push({
//                 "no" : MembArr[i].no,
//                 "memberName" : MembArr[i].memberName,
//               })
//             }
//           }
//         } 

//         $(".List-Container").show();
//         xMembListCon.innerHTML = newDivGenerator(FinalChoicedMember);
//         $("#memberlist-modal").modal("hide");
//       }) //모달창에서 적용하기 버튼 click event END


//       //모달창 내 멤버 전체 버튼 클릭 EVENT START
//       $("#allMemb").on("click", function() {
//         $.ajax({
//           url : "/joinmember/grouplistbygno",
//           type : "POST",
//           data : {"group.no": gno},
//           success: function(result) {
//             // console.log(AllMember);
//             let MembArr = []; //멤버번호랑, 이름만 있는 배열

//             for (i=0; i < AllMember.length; i++) { //멤버리스트 배열로 빼기
//               MembArr.push(AllMember[i].member);
//             }

//             $(".List-Container").show();
//             xMembListCon.innerHTML = newDivGenerator(MembArr); 
//             $("#memberlist-modal").modal("hide");
//           } //success END
//         })//ajax END
//       })//모달창 내 멤버 전체 버튼 클릭 EVENT END
//     } //success END
//   })//ajax END
// }) //멤버리스트 버튼 클릭 EVENT START END

//불러온 멤버 개별 div의 삭제 이벤트               !!!!!!!!!!!!!!!!!!!!!!!!!나중에 하나로 합치든가 코드 정리하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
function deleteDiv(e) {
  xMembListCon.removeChild(e.target.parentNode);
  
  let memberCount = xMembListCon.childElementCount;
  // console.log(memberCount);
    if (memberCount == 0) {
      $(".List-Container").hide(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DIV 수정하면 count 숫자 바꾸기!!!!!!!!!!!!!!!!!!!
    // $("#x-membList-container").empty();
    }
};

//멤버 변경 개별 div의 삭제 이벤트
function newDeleteDiv(e) {
  xMembListCon.removeChild(e.target.parentNode);
  
  let memberCount = xMembListCon.childElementCount;
  // console.log(memberCount);
    if (memberCount == 0) {
      $(".List-Container").hide();
    // $("#x-membList-container").empty();
    }
};

//멤버 초기화 버튼
$("#cleanMemb").on("click", function() {
  $(".List-Container").hide();
});
$(document).ready(function () {
  $('#header').load('/junho/mainHeader.html');
  $('#footers').load('/junho/mainfooter.html');
  $("#listnone").hide();
  $("#sendlistnone").hide();
});

$.ajax({
  url : "/member/getLoginUser",
  type : "POST",
  success: function (result) {
    let memberInfo = result.data;
    if (result.status == "fail") {
      location.href="/junho/index.html";
    } else {

      $.ajax({
        url: "/invitebox/invitelistbyrecipientpaing",
        type: "POST",
        datatype: "json",
        data: {
          "pageSize": 3,
          "pageNo": 1,
          "memberNo" : memberInfo.no
        },
        success: function (msgResult) {
          console.log(msgResult);
          let totalListCount = msgResult.totalListCount;
          let totalPageSize = msgResult.totalPageSize;
          let requestPageNo = msgResult.pageNo;
          let recivedTbody = document.querySelector(".recived-table tbody");
          let recivedTrTemplate = document.querySelector("#recived-tr-template");
          let recivedTableGenerator = Handlebars.compile(recivedTrTemplate.innerHTML);

          if (totalListCount != 0) {
            $("#recivedlistnone").hide();
            recivedTbody.innerHTML = recivedTableGenerator(msgResult.data);
          } else {
            $("#recivedlistnone").show();
          }

          $("#recivedpageNo").text(requestPageNo);

          if (requestPageNo == 1) {
            $("#recivedpreA").hide();
          } else {
            $("#recivedpreA").show();
          }
    
          if (totalPageSize == requestPageNo) {
            $("#recivednextA").hide();
          } else {
            $("#recivednextA").show();
          }
          
          $("#recivedpreA").val(requestPageNo - 1);
          $("#recivednextA").val(requestPageNo + 1);

          //요청 메세지 모달 띄우기
          $(".recived-title").on("click", function (e) {
          $("#recivedModal").modal("show");
          $.ajax({
            url: "/invitebox/recivedinvite",
            type: "POST",
            data: {
              inviteNo : $(e.target).attr("value")
            },
            success: function (recivedInviteResult) {
              $("#recived-title").val(recivedInviteResult.data.title);
              $("#inviteNo").val(recivedInviteResult.data.inviteNo);
              $("#invite-group-name").val(recivedInviteResult.data.joinMember.group.groupName); 
              $("#invite-group-no").val(recivedInviteResult.data.joinMember.group.no); 
              $("#sender-no").val(recivedInviteResult.data.joinMember.member.no); 
              $("#sender-name").val(recivedInviteResult.data.joinMember.member.memberName); 
              $("#invite-content").val(recivedInviteResult.data.content); 

              //수신확인 insert
              let formdata = new FormData(document.forms.namedItem("confirm-form"))
              formdata.append("inviteNo", $("#inviteNo").val());
              formdata.append("confirm", 1);
              
              fetch("/invitebox/confirmupdate", {
                method: "POST",
                body: new URLSearchParams(formdata)
                })
                .then(function(response){
                  return response.json();
                })
                .then(function(result){
                  // console.log(result);
                });
              
              //가입확인
              joinMemberArr = [];
              $.ajax({
                url: "/joinmember/grouplistbygno",
                type: "POST",
                data: {"group.no" : $("#invite-group-no").val()},
                success: function (result) {
                  for (i = 0; i < result.data.length; i++) {
                    joinMemberArr.push({
                      "no": result.data[i].member.no,
                      "memberName" : result.data[i].member.memberName
                    })  
                  }
                  for (i = 0; i < joinMemberArr.length; i++) {
                    if (joinMemberArr[i].no == memberInfo.no) {
                      window.alert("가입된 모임입니다.");
                      $(".btn-accept").hide();
                      $(".btn-reject").text("삭제");
                    } 
                  }
                  
                  //가입
                  $(".btn-accept").on("click", function () {
                    console.log("클릭");
                    $.ajax({
                      url: "/joinmember/insertjoingroup",
                      type: "POST",
                      data: {
                        "group.no": $("#invite-group-no").val(),
                        "memberGrade.gradeNo" : 2  },
                      success: function (insertResult) {
                        console.log(insertResult);
                        window.alert("가입되었습니다.")
                        location.href = "/minkyu/mypage/pickme.html";
                      }
                    })
                  }) //가입 끝

                  //거절버튼
                  $(".btn-reject").on("click", function () {
                    console.log($(e.target).attr("value"));
                    $.ajax({
                      url: '/invitebox/delete',
                      type: "POST",
                      data: { "no": $(e.target).attr("value")},
                      success: function (result) {
                        if ($(".btn-reject").text() == "거절") {
                          window.alert("거절되었습니다.")
                          location.href = "/minkyu/mypage/pickme.html";
                        } else {
                          window.alert("삭제되었습니다.")
                          location.href = "/minkyu/mypage/pickme.html"; 
                        }  
                      }
                    })
                  }) //삭제 끝
                }
              })                     


            }
          })
          }) //모달 띄우기 END
          
          //초대 요청 다음버튼
          $("#recivednextA").on("click", function () {
            console.log("클릭");
            $.ajax({ //픽미 게시글 화면에 뿌리기
              url: "/invitebox/invitelistbyrecipientpaing",
              type: "POST",
              datatype: "json",
              data: {
                pageSize: 3,
                pageNo: $("#recivednextA").val(),
                memberNo: memberInfo.no
              },
              success: function (recivednextResult) {
                totalListCount = recivednextResult.totalListCount;
                totalPageSize = recivednextResult.totalPageSize;
                requestPageNo = recivednextResult.pageNo;

                // console.log($("#recivedpreA").val());
                // console.log($("#recivednextA").val());

                $("#recivedpageNo").text(requestPageNo);
                $("#recivedpreA").val(requestPageNo - 1);
                $("#recivednextA").val(requestPageNo + 1);

                recivedTbody.innerHTML = recivedTableGenerator(recivednextResult.data);

                if (requestPageNo == 1) {
                  $("#recivedpreA").hide();
                } else {
                  $("#recivedpreA").show();
                }
        
                if (totalPageSize == requestPageNo) {
                  $("#recivednextA").hide();
                } else {
                  $("#recivednextA").show();
                }

                  //요청 메세지 모달 띄우기
                $(".recived-title").on("click", function (e) {
                  $("#recivedModal").modal("show");
                  $.ajax({
                    url: "/invitebox/recivedinvite",
                    type: "POST",
                    data: {
                      inviteNo : $(e.target).attr("value")
                    },
                    success: function (recivedInviteResult) {
                      // console.log(recivedInviteResult.data);
                      // console.log($("#recipient-title"));
                      $("#recived-title").val(recivedInviteResult.data.title);
                      $("#inviteNo").val(recivedInviteResult.data.inviteNo);
                      $("#invite-group-name").val(recivedInviteResult.data.joinMember.group.groupName); 
                      $("#invite-group-no").val(recivedInviteResult.data.joinMember.group.no); 
                      $("#sender-no").val(recivedInviteResult.data.joinMember.member.no); 
                      $("#sender-name").val(recivedInviteResult.data.joinMember.member.memberName); 
                      $("#invite-content").val(recivedInviteResult.data.content); 
        
                      //수신확인 insert
                      let formdata = new FormData(document.forms.namedItem("confirm-form"))
                      formdata.append("inviteNo", $("#inviteNo").val());
                      formdata.append("confirm", 1);
                      
                      fetch("/invitebox/confirmupdate", {
                        method: "POST",
                        body: new URLSearchParams(formdata)
                        })
                        .then(function(response){
                          return response.json();
                        })
                        .then(function(result){
                          // console.log(result);
                        });
                      
                      //가입확인
                      joinMemberArr = [];
                      $.ajax({
                        url: "/joinmember/grouplistbygno",
                        type: "POST",
                        data: {"group.no" : $("#invite-group-no").val()},
                        success: function (result) {
                          for (i = 0; i < result.data.length; i++) {
                            joinMemberArr.push({
                              "no": result.data[i].member.no,
                              "memberName" : result.data[i].member.memberName
                            })  
                          }
                          for (i = 0; i < joinMemberArr.length; i++) {
                            if (joinMemberArr[i].no == memberInfo.no) {
                              window.alert("가입된 모임입니다.");
                              $(".btn-accept").hide();
                              $(".btn-reject").text("삭제");
                            } 
                          }
                          
                          //가입
                          $(".btn-accept").on("click", function () {
                            console.log("클릭");
                            $.ajax({
                              url: "/joinmember/insertjoingroup",
                              type: "POST",
                              data: {
                                "group.no": $("#invite-group-no").val(),
                                "memberGrade.gradeNo" : 2  },
                              success: function (insertResult) {
                                console.log(insertResult);
                                window.alert("가입되었습니다.")
                                location.href = "/minkyu/mypage/pickme.html";
                              }
                            })
                          }) //가입 끝

                          //거절버튼
                          $(".btn-reject").on("click", function () {
                            console.log($(e.target).attr("value"));
                            $.ajax({
                              url: '/invitebox/delete',
                              type: "POST",
                              data: { "no": $(e.target).attr("value")},
                              success: function (result) {
                                if ($(".btn-reject").text() == "거절") {
                                  window.alert("거절되었습니다.")
                                  location.href = "/minkyu/mypage/pickme.html";
                                } else {
                                  window.alert("삭제되었습니다.")
                                  location.href = "/minkyu/mypage/pickme.html"; 
                                }  
                              }
                            })
                          }) //삭제 끝

                        }
                      })                     
                    }
                  })
                }) //모달 띄우기 END
              }
            })
          }); 

          //초대 요청 이전버튼
          $("#recivedpreA").on("click", function () {
            $.ajax({ //픽미 게시글 화면에 뿌리기
              url: "/invitebox/invitelistbyrecipientpaing",
              type: "POST",
              datatype: "json",
              data: {
                pageSize: 3,
                pageNo: $("#recivedpreA").val(),
                memberNo: memberInfo.no
              },
              success: function (recivedpreResult) {
                totalListCount = recivedpreResult.totalListCount;
                totalPageSize = recivedpreResult.totalPageSize;
                requestPageNo = recivedpreResult.pageNo;
                $("#recivedpageNo").text(requestPageNo);
                $("#recivedpreA").val(requestPageNo - 1);
                $("#recivednextA").val(requestPageNo + 1);

                recivedTbody.innerHTML = recivedTableGenerator(recivedpreResult.data);

                if (requestPageNo == 1) {
                  $("#recivedpreA").hide();
                } else {
                  $("#recivedpreA").show();
                }
        
                if (totalPageSize == requestPageNo) {
                  $("#recivednextA").hide();
                } else {
                  $("#recivednextA").show();
                }
              
                //요청 메세지 모달 띄우기
                $(".recived-title").on("click", function (e) {
                  $("#recivedModal").modal("show");
                  $.ajax({
                    url: "/invitebox/recivedinvite",
                    type: "POST",
                    data: {
                      inviteNo : $(e.target).attr("value")
                    },
                    success: function (recivedInviteResult) {
                      // console.log(recivedInviteResult.data);
                      // console.log($("#recipient-title"));
                      $("#recived-title").val(recivedInviteResult.data.title);
                      $("#inviteNo").val(recivedInviteResult.data.inviteNo);
                      $("#invite-group-name").val(recivedInviteResult.data.joinMember.group.groupName); 
                      $("#invite-group-no").val(recivedInviteResult.data.joinMember.group.no); 
                      $("#sender-no").val(recivedInviteResult.data.joinMember.member.no); 
                      $("#sender-name").val(recivedInviteResult.data.joinMember.member.memberName); 
                      $("#invite-content").val(recivedInviteResult.data.content); 

                      //수신확인 insert
                      let formdata = new FormData(document.forms.namedItem("confirm-form"))
                      formdata.append("inviteNo", $("#inviteNo").val());
                      formdata.append("confirm", 1);
                      
                      fetch("/invitebox/confirmupdate", {
                        method: "POST",
                        body: new URLSearchParams(formdata)
                        })
                        .then(function(response){
                          return response.json();
                        })
                        .then(function(result){
                          // console.log(result);
                        });
                      
                      //가입확인
                      joinMemberArr = [];
                      $.ajax({
                        url: "/joinmember/grouplistbygno",
                        type: "POST",
                        data: {"group.no" : $("#invite-group-no").val()},
                        success: function (result) {
                          for (i = 0; i < result.data.length; i++) {
                            joinMemberArr.push({
                              "no": result.data[i].member.no,
                              "memberName" : result.data[i].member.memberName
                            })  
                          }
                          for (i = 0; i < joinMemberArr.length; i++) {
                            if (joinMemberArr[i].no == memberInfo.no) {
                              window.alert("가입된 모임입니다.");
                              $(".btn-accept").hide();
                              $(".btn-reject").text("삭제");
                            } 
                          }
                          
                          //가입
                          $(".btn-accept").on("click", function () {
                            console.log("클릭");
                            $.ajax({
                              url: "/joinmember/insertjoingroup",
                              type: "POST",
                              data: {
                                "group.no": $("#invite-group-no").val(),
                                "memberGrade.gradeNo" : 2  },
                              success: function (insertResult) {
                                console.log(insertResult);
                                window.alert("가입되었습니다.")
                                location.href = "/minkyu/mypage/pickme.html";
                              }
                            })
                          }) //가입 끝

                          //거절버튼
                          $(".btn-reject").on("click", function () {
                            // console.log($(e.target).attr("value"));
                            $.ajax({
                              url: '/invitebox/delete',
                              type: "POST",
                              data: { "no": $(e.target).attr("value")},
                              success: function (result) {
                                if ($(".btn-reject").text() == "거절") {
                                  window.alert("거절되었습니다.")
                                  location.href = "/minkyu/mypage/pickme.html";
                                } else {
                                  window.alert("삭제되었습니다.")
                                  location.href = "/minkyu/mypage/pickme.html"; 
                                }  
                              }
                            })
                          }) //삭제 끝
                        }
                      })
                    }
                  })
                }) //모달 띄우기 END
              }
            })
          }); //초대 요청 이전 버튼 END

        } //success END
      }) //ajax END

//----------------------------초대가 온 픽미-------------------------------------

      // 초대 메세지 보낸거 뿌리기
      $.ajax({
        url: "/invitebox/invitelistbysender",
        type : "POST",
        data: {
          "pageSize": 3,
          "pageNo": 1,
          "memberNo": memberInfo.no
        },
        success: function (invitebySender) {
          let senderData = invitebySender.data;
          console.log(senderData);
          let senderTbody = document.querySelector(".sender-table tbody");
          let senderTrTemplate = document.querySelector("#sender-tr-template");
          let senderTableGenerator = Handlebars.compile(senderTrTemplate.innerHTML);
          let totalListCount = invitebySender.totalListCount;
          let totalPageSize = invitebySender.totalPageSize;
          let requestPageNo = invitebySender.pageNo;
          invitebySenderArr = [];
          finalinvitebySenderArr = [];

          //읽음 읽지않음으로 배열만듦
          for (i = 0; i < senderData.length; i++) {
            let xtrue = "읽음";
            let xfalse = "읽지 않음";

            invitebySenderArr.push({
              inviteNo: senderData[i].inviteNo,
              groupNo: senderData[i].joinMember.group.no,
              groupName: senderData[i].joinMember.group.groupName,
              content: senderData[i].content,              
              recipientNo: senderData[i].member.no,
              recipientName: senderData[i].member.memberName,
              regDt: senderData[i].regDt,
              title: senderData[i].title,
              confirm: senderData[i].confirm,
              confirmName: senderData[i].confirm == true ? xtrue : xfalse
            })
          }

          if (totalListCount != 0) {
            $("#sendlistnone").hide();
            senderTbody.innerHTML = senderTableGenerator(invitebySenderArr);
          } else {
            $("#sendlistnone").show();
          }

         $("#sendpageNo").text(requestPageNo); //번호 출력

          if (requestPageNo == 1) {
            $("#sendpreA").hide();
          } else {
            $("#sendpreA").show();
          }
    
          if (totalPageSize == requestPageNo) {
            $("#sendnextA").hide();
          } else {
            $("#sendnextA").show();
          }
          
          $("#sendpreA").val(requestPageNo - 1);
          $("#sendnextA").val(requestPageNo + 1);

          // let XsendTitle = document.querySelector("#invite-title");
          // let XrecipientName = document.querySelector("#recipient-name");
          // let XrecipientNo = document.querySelector("#recipient-no");
          // let xinvitegroupname = document.querySelector("#invite-group-name");
          // let xinvitegroupno = document.querySelector("#invite-group-no");
          // let xinvitecontent = document.querySelector("#invite-content");

          //모달 출력
          $(".title").on("click", function (e) {
            $("#sendModal").modal("show");
            $.ajax({
              url: "/invitebox/get",
              type: "POST",
              data: {
                inviteNo : $(e.target).attr("value"),
                senderNo : memberInfo.no
              },
              success: function (sendInviteResult) {
                console.log(sendInviteResult.data.joinMember.group.groupName);
                $("#invite-title").val(sendInviteResult.data.title); 
                $("#inviteNo").val(sendInviteResult.data.inviteNo);
                $("#invitee-group-name").val(sendInviteResult.data.joinMember.group.groupName); 
                $("#invitee-group-no").val(sendInviteResult.data.joinMember.group.no); 
                $("#recipient-nos").val(sendInviteResult.data.member.no); 
                $("#recipient-names").val(sendInviteResult.data.member.memberName); 
                $("#invitee-content").val(sendInviteResult.data.content); 

                if ($(e.target.closest(".sender-table")).hasClass("sender-table") === true) {
                  if ($(e.target).siblings(".confirm").text() == "읽음") {
                    $("#invite-title").attr("readonly", true);
                    $(".request-content").attr("readonly", true);
                    $("#updateBtn").attr("hidden", true);
                    $("#sendDeleteBtn").attr("hidden", true);
                  } else {
                    $("#invite-title").attr("readonly", false);
                    $(".request-content").attr("readonly", false);
                    $("#updateBtn").attr("hidden", false);
                    $("#sendDeleteBtn").attr("hidden", false);
                  }
                }// 읽음 확인 if END
                
                //삭제
                $("#sendDeleteBtn").on("click", function () {
                  console.log("클릭");
                  $.ajax({
                    url: '/invitebox/delete',
                    type: "POST",
                    data: { "no": $(e.target).attr("value")},
                    success: function (result) {
                      console.log(result);
                      window.alert("삭제되었습니다.")
                      location.href = "/minkyu/mypage/pickme.html";
                    }
                  })
                }) //삭제 끝

              }
            })
          }) //모달 띄우기 END

          //요청 다음버튼
          $("#sendnextA").on("click", function () {
            $.ajax({ //픽미 게시글 화면에 뿌리기
              url: "/invitebox/invitelistbysender",
              type: "POST",
              datatype: "json",
              data: {
                pageSize: 3,
                pageNo: $("#sendnextA").val(),
                memberNo: memberInfo.no
              },
              success: function (sendnextResult) {
                totalListCount = sendnextResult.totalListCount;
                totalPageSize = sendnextResult.totalPageSize;
                requestPageNo = sendnextResult.pageNo;
                nextinvitebySenderArr = [];
                $("#sendpageNo").text(requestPageNo);
                $("#sendpreA").val(requestPageNo - 1);
                $("#sendnextA").val(requestPageNo + 1);

                for (i = 0; i < sendnextResult.data.length; i++) {
                  let xtrue = "읽음";
                  let xfalse = "읽지 않음";
                  nextinvitebySenderArr.push({
                    inviteNo: sendnextResult.data[i].inviteNo,
                    groupNo: sendnextResult.data[i].joinMember.group.no,
                    groupName: sendnextResult.data[i].joinMember.group.groupName,
                    content: sendnextResult.data[i].content,
                    recipientNo: sendnextResult.data[i].member.no,
                    recipientName: sendnextResult.data[i].member.memberName,
                    regDt: sendnextResult.data[i].regDt,
                    title: sendnextResult.data[i].title,
                    confirm: sendnextResult.data[i].confirm,
                    confirmName: sendnextResult.data[i].confirm == true ? xtrue : xfalse
                  })
                }
                senderTbody.innerHTML = senderTableGenerator(nextinvitebySenderArr);

                if (requestPageNo == 1) {
                  $("#sendpreA").hide();
                } else {
                  $("#sendpreA").show();
                }
        
                if (totalPageSize == requestPageNo) {
                  $("#sendnextA").hide();
                } else {
                  $("#sendnextA").show();
                }

                $(".title").on("click", function (e) {
                  $("#sendModal").modal("show");
                  $.ajax({
                    url: "/invitebox/get",
                    type: "POST",
                    data: {
                      inviteNo : $(e.target).attr("value"),
                      senderNo : memberInfo.no
                    },
                    success: function (sendInviteResult) {
                      console.log(sendInviteResult.data.joinMember.group.groupName);
                      $("#invite-title").val(sendInviteResult.data.title); 
                      $("#inviteNo").val(sendInviteResult.data.inviteNo);
                      $("#invitee-group-name").val(sendInviteResult.data.joinMember.group.groupName); 
                      $("#invitee-group-no").val(sendInviteResult.data.joinMember.group.no); 
                      $("#recipient-nos").val(sendInviteResult.data.member.no); 
                      $("#recipient-names").val(sendInviteResult.data.member.memberName); 
                      $("#invitee-content").val(sendInviteResult.data.content); 

                      if ($(e.target.closest(".sender-table")).hasClass("sender-table") === true) {
                        if ($(e.target).siblings(".confirm").text() == "읽음") {
                          $("#invite-title").attr("readonly", true);
                          $(".request-content").attr("readonly", true);
                          $("#updateBtn").attr("hidden", true);
                          $("#sendDeleteBtn").attr("hidden", true);
                        } else {
                          $("#invite-title").attr("readonly", false);
                          $(".request-content").attr("readonly", false);
                          $("#updateBtn").attr("hidden", false);
                          $("#sendDeleteBtn").attr("hidden", false);
                        }
                      }// 읽음 확인 if END
                      
                      //삭제
                      $("#sendDeleteBtn").on("click", function () {
                        console.log("클릭");
                        $.ajax({
                          url: '/invitebox/delete',
                          type: "POST",
                          data: { "no": $(e.target).attr("value")},
                          success: function(result) {
                            window.alert("삭제되었습니다.")
                            location.href = "/minkyu/mypage/pickme.html";
                          }
                        })
                      }) //삭제 끝

                    }
                  })
                }) //모달 띄우기 END

              }
            })
          }); //$("#sendnextA") click Event END

          //요청 이전버튼
          $("#sendpreA").on("click", function () {
            $.ajax({ //픽미 게시글 화면에 뿌리기
              url: "/invitebox/invitelistbysender",
              type: "POST",
              datatype: "json",
              data: {
                pageSize: 3,
                pageNo: $("#sendpreA").val(),
                memberNo: memberInfo.no
              },
              success: function (sendpreResult) {
                totalListCount = sendpreResult.totalListCount;
                totalPageSize = sendpreResult.totalPageSize;
                requestPageNo = sendpreResult.pageNo;
                preinvitebySenderArr = [];
                $("#sendpageNo").text(requestPageNo);
                $("#sendpreA").val(requestPageNo - 1);
                $("#sendnextA").val(requestPageNo + 1);

                for (i = 0; i < sendpreResult.data.length; i++) {
                  let xtrue = "읽음";
                  let xfalse = "읽지 않음";
                  preinvitebySenderArr.push({
                    inviteNo: sendpreResult.data[i].inviteNo,
                    groupNo: sendpreResult.data[i].joinMember.group.no,
                    groupName: sendpreResult.data[i].joinMember.group.groupName,
                    content: sendpreResult.data[i].content,
                    recipientNo: sendpreResult.data[i].member.no,
                    recipientName: sendpreResult.data[i].member.memberName,
                    regDt: sendpreResult.data[i].regDt,
                    title: sendpreResult.data[i].title,
                    confirm: sendpreResult.data[i].confirm,
                    confirmName: sendpreResult.data[i].confirm == true ? xtrue : xfalse
                  })
                }
                senderTbody.innerHTML = senderTableGenerator(preinvitebySenderArr);

                if (requestPageNo == 1) {
                  $("#sendpreA").hide();
                } else {
                  $("#sendpreA").show();
                }
        
                if (totalPageSize == requestPageNo) {
                  $("#sendnextA").hide();
                } else {
                  $("#sendnextA").show();
                }

                          //요청 메세지 모달 띄우기
                $(".title").on("click", function (e) {
                  $("#sendModal").modal("show");
                  $.ajax({
                    url: "/invitebox/get",
                    type: "POST",
                    data: {
                      inviteNo : $(e.target).attr("value"),
                      senderNo : memberInfo.no
                    },
                    success: function (sendInviteResult) {
                      console.log(sendInviteResult.data.joinMember.group.groupName);
                      $("#invite-title").val(sendInviteResult.data.title); 
                      $("#inviteNo").val(sendInviteResult.data.inviteNo);
                      $("#invitee-group-name").val(sendInviteResult.data.joinMember.group.groupName); 
                      $("#invitee-group-no").val(sendInviteResult.data.joinMember.group.no); 
                      $("#recipient-nos").val(sendInviteResult.data.member.no); 
                      $("#recipient-names").val(sendInviteResult.data.member.memberName); 
                      $("#invitee-content").val(sendInviteResult.data.content); 

                      if ($(e.target.closest(".sender-table")).hasClass("sender-table") === true) {
                        if ($(e.target).siblings(".confirm").text() == "읽음") {
                          $("#invite-title").attr("readonly", true);
                          $(".request-content").attr("readonly", true);
                          $("#updateBtn").attr("hidden", true);
                          $("#sendDeleteBtn").attr("hidden", true);
                        } else {
                          $("#invite-title").attr("readonly", false);
                          $(".request-content").attr("readonly", false);
                          $("#updateBtn").attr("hidden", false);
                          $("#sendDeleteBtn").attr("hidden", false);
                        }
                      }// 읽음 확인 if END
                      
                      //삭제
                      $("#sendDeleteBtn").on("click", function () {
                        console.log($(e.target).attr("value"));
                        console.log("클릭");
                        $.ajax({
                          url: '/invitebox/delete',
                          type: "POST",
                          data: { "no": $(e.target).attr("value")},
                          success: function(result) {
                            window.alert("삭제되었습니다.")
                            location.href = "/minkyu/mypage/pickme.html";
                          }
                        })
                      }) //삭제 끝

                    }
                  })
                }) //모달 띄우기 END
              }
            })
          }); //$("#sendpreA") click Event END

          //모달에 값 전달 function
          // $(".modal-request").on("show") //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!여기부터 해



          // 수정
          $("#updateBtn").on("click", function () {
            let fd = new FormData(document.forms.namedItem("send-form"));

            fd.append("inviteNo", $("#senderTitle").attr("value"))
            console.log(fd);
            
            fetch("/invitebox/update", {
              method: "POST",
              body: new URLSearchParams(fd)
              })
              .then(function(response){
                return response.json();
              })
              .then(function(result){
                console.log(result);
                if (result.status == "success") {
                  window.alert("수정되었습니다.");
                  location.href = "/minkyu/mypage/pickme.html";
                } else {
                  window.alert("수정 실패!");
                  console.log(result.data);
                }
              });
          })

    
          $(".send-all-check").click(function (e) {
            if ($(".send-all-check").is(":checked")) {
              $(".sender-table").find("input").prop("checked", true)
            } else {
              $(".sender-table").find("input").prop("checked", false)
            }
          });

          $("#send-deleteBtn").on("click", function () {
            console.log("클릭");
            let deleteArr = [];
            $("input:checkbox[name=no]:checked").each(function() { 
              var choiceno = $(this).val();
              deleteArr.push({no : choiceno})
            })
            console.log(deleteArr);

            for (i = 0; i < deleteArr.length; i++) {
              $.ajax({
                url: '/invitebox/delete',
                type: "POST",
                data: { "no": deleteArr[i].no},
                success: function(result) {
                  window.alert("삭제되었습니다.")
                  location.href = "/minkyu/mypage/pickme.html";
                }
              })
            }
          }) //$("#send-deleteBtn") END
        }
      }) //초대 메세지 보낸거 뿌리기 END

      // 픽미 게시글 화면에 뿌리기
      $.ajax({ 
        url: "/pickme/mypagelist",
        type: "POST",
        datatype: "json",
        data: {
          "pageSize": 3,
          "pageNo": 1,
          "memberNo" : memberInfo.no
        },
        success: function (pickmeResult) {
          // console.log(pickmeResult);
          let tbody = document.querySelector("#pickmeTable tbody");
          let trTemplate = document.querySelector("#tr-template");  
          let htmlGenerator = Handlebars.compile(trTemplate.innerHTML);
          let totalListCount = pickmeResult.totalListCount;
          let totalPageSize = pickmeResult.totalPageSize;
          let requestPageNo = pickmeResult.pageNo;
          
          if (totalListCount != 0) {
            tbody.innerHTML = htmlGenerator(pickmeResult.data);
            $("#pickme-deleteBtn").show();
          } else {
            $("#listnone").show();
            $("#pickme-deleteBtn").hide();
          }
          
          $("#pageNo").text(requestPageNo); //번호 출력

          if (requestPageNo == 1) {
            $("#preA").hide();
          } else {
            $("#preA").show();
          }
    
          if (totalPageSize == requestPageNo) {
            $("#nextA").hide();
          } else {
            $("#nextA").show();
          }
          
          $("#preA").val(requestPageNo - 1);
          $("#nextA").val(requestPageNo + 1);
    
          $("#nextA").on("click", function () {
            $.ajax({ //픽미 게시글 화면에 뿌리기
              url: "/pickme/mypagelist",
              type: "POST",
              datatype: "json",
              data: { pageSize: 3,
                      pageNo: $("#nextA").val(),
                      memberNo: memberInfo.no},
              success: function (pickmeResult) {
                totalListCount = pickmeResult.totalListCount;
                totalPageSize = pickmeResult.totalPageSize;
                requestPageNo = pickmeResult.pageNo;
                $("#pageNo").text(requestPageNo);
                $("#preA").val(requestPageNo - 1);
                $("#nextA").val(requestPageNo + 1);
                tbody.innerHTML = htmlGenerator(pickmeResult.data);

                if (requestPageNo == 1) {
                  $("#preA").hide();
                } else {
                  $("#preA").show();
                }
        
                if (totalPageSize == requestPageNo) {
                  $("#nextA").hide();
                } else {
                  $("#nextA").show();
              }
              }
            })
          })

          $("#preA").on("click", function () { 
            $.ajax({ //픽미 게시글 화면에 뿌리기
              url: "/pickme/mypagelist",
              type: "POST",
              datatype: "json",
              data: { pageSize: 3,
                      pageNo: $("#preA").val(),
                      memberNo: memberInfo.no},
              success: function (pickmeResult) {
                totalListCount = pickmeResult.totalListCount;
                totalPageSize = pickmeResult.totalPageSize;
                requestPageNo = pickmeResult.pageNo;
                $("#pageNo").text(requestPageNo);
                $("#preA").val(requestPageNo - 1);
                $("#nextA").val(requestPageNo + 1);
                tbody.innerHTML = htmlGenerator(pickmeResult.data);

                if (requestPageNo == 1) {
                  $("#preA").hide();
                } else {
                  $("#preA").show();
                }
        
                if (totalPageSize == requestPageNo) {
                  $("#nextA").hide();
                } else {
                  $("#nextA").show();
                }
              }
            })
          })

          //삭제 전체 체크
          $(".pickme-all-check").click(function (e) {
            if ($(".pickme-all-check").is(":checked")) {
              $(".pickme-table").find("input").prop("checked", true)
            } else {
              $(".pickme-table").find("input").prop("checked", false)
            }
          });

          //삭제
          $("#pickme-deleteBtn").on("click", function () {
            console.log("클릭");
            let deleteArr = [];
            $("input:checkbox[name=no]:checked").each(function() { 
              var choiceno = $(this).val();
              deleteArr.push({no : choiceno})
            })
            
            for (i = 0; i < deleteArr.length; i++) {
              $.ajax({
                url: '/pickme/delete',
                type: "POST",
                data: { "no": deleteArr[i].no},
                success: function(result) {
                  window.alert("삭제되었습니다.")
                  location.href = "/minkyu/mypage/pickme.html";
                }
              })
            }         
          });
        }// pickme list success END
      })// pickme list END

    } //로그인 됐으면
  } //로그인 success END
}); //로그인 여부 확인 ajax END

$(document).ready(function () {
    $('#header').load('/junho/mainHeader.html'); 
    $('#top').load('/ssang/groupBoard/none-search-top.html');
    $('#footer').load('/junho/mainfooter.html'); 
  });

var calNoparameter = 0;
var arr = location.href.split("?");
  if (arr.length == 1) {
    alert("요청 형식이 올바르지 않습니다.")
    throw "URL 형식 오류!";
  }
  var qs = arr[1];
  var params = new URLSearchParams(qs);
  var gno = params.get("gno");

//로그인 여부 확인
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
            $(".fc-addEventButton-button").hide();
            $("#Gomidpoint-Btn").hide();
            $("#modal-UpdateBtn").hide();
            $("#modal-DeleteBtn").hide();
            $("#view-modal-title").attr("disabled", true);
            $("#view-modal-content").attr("disabled", true);
            $("#view-modal-gno").attr("disabled", true);
            $("#view-modal-startDt").attr("disabled", true);
            $("#view-modal-endDt").attr("disabled", true);
          }//회원 등급 조회 if END    
        }//회원 등급 조회 success END           
      })//회원 등급 조회 ajax END 
    }//로그인 확인 else END
  }//로그인 여부 확인 success END
}); //로그인 여부 확인 ajax END  

document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
    height: 600, // calendar 높이 설정
    expandRows: true, // 화면에 맞게 높이 재설정
    handleWindowResize: true, //브라우저 창의 크기를 조정할 때 캘린더 크기를 자동으로 조정
    timeZone: 'local',
    initialView: 'dayGridMonth', //월만 보이게함
    editable: false, // false로 변경 시 draggable 작동 x 
    displayEventTime: false, // 시간 표시 x
    headerToolbar: {center: 'addEventButton'}, // headerToolbar에 버튼을 추가

    events: function(info, successCallback, failureCallback) { //캘린더 화면에 뿌리기
        $.ajax({
                url: '/calendar/listbygroup',
                type: 'POST',
                async : false, //비동기 off
                datatype : 'json',
                data : {"joinMemb.group.no" : gno},
                success: function(param) {
                    var events = [];
                    $.each(param.data, function(index, data){
                    events.push({
                        id : data.no,
                        title : data.name,
                        start : data.startDt,
                        end: data.endDt + 'T23:59:59'
                        });
                    }) //each END
                    successCallback(events);
                }// success END
            }) //캘린더 리스트 불러오는ajax END
    },//events ajax list function END
        
    eventClick: function(info) { //날짜를 클릭하면 나오는 이벤트
    var eventObj = info.event;
      console.log(eventObj);
      calNoparameter = eventObj.id;
      var urlInput = document.querySelector('input[type="url"]')
      urlInput.value = `/junho/midpoint/membermidpoint.html?gno=${gno}&cal_no=${calNoparameter}`;
      urlInput.addEventListener('click' ,function() {
          window.location.href = `/junho/midpoint/membermidpoint.html?gno=${gno}&cal_no=${calNoparameter}`;
      })
      $("#Gomidpoint-Btn").on("click", function() {
          window.location.href=`/junho/midpoint/inviteindex.html?gno=${gno}&cal_no=${eventObj.id}`;
      })
        $.ajax({
            url: '/calendar/get',
            type: 'POST',
            async : false, //비동기 off
            data: { no: eventObj.id },
            success: function(param) {
                console.log(param);
                    let data = param.data;
                console.log($("#view-Modal"));
                $("#view-Modal").modal("show"); //상세보기 모달창 나타냄
                //값 출력
                // console.log(data.joinMemb.group.no);
                $("#view-modal-title").val(data.name);
                $("#view-modal-content").val(data.content);
                $("#view-modal-gno").val(data.joinMemb.group.no);
                $("#view-modal-startDt").val(data.startDt);
                $("#view-modal-endDt").val(data.endDt);
                // $("#view-modal-resultURL").val(data.resulturl);
                if ($("#view-modal-resultURL").val() != null){
                } else {
                    $("#view-modal-resultURL").val(" ");
                }

                $("#modal-DeleteBtn").on("click", function() { 
                    $.ajax({
                        url: '/calendar/delete',
                        type: 'POST',
                        data: { no: eventObj.id },
                            success: function(data) {
                                    // console.log(data.status);
                                    if(data.status == 'fail') {
                                        alert("삭제 권한이 없습니다.");
                                        window.location.href = "index.html?gno=" + gno;
                                    } else {
                                        alert('삭제되었습니다.');
                                        window.location.href = "index.html?gno=" + gno;
                                    }                                        
                                }
                            }) //delete요청 ajax END
                            // window.location.reload();
                        })// deletebtn function END
                
                //변경 버튼
                $("#modal-UpdateBtn").on("click", function() { //UpdateBtn function
                    //내용 입력 여부 확인
                    if($("#view-modal-content").val() == null || $("#view-modal-content").val() == ""){
                            alert("내용을 입력하세요.");
                        }else if($("#view-modal-startDt").val() == "" || $("#view-modal-endDt").val() ==""){
                            alert("날짜를 입력하세요.");
                        }else if(new Date($("#view-modal-endDt").val())- new Date($("#view-modal-startDt").val()) < 0){ // date 타입으로 변경 후 확인
                            alert("종료일이 시작일보다 먼저입니다.");
                        }else{ //정상 입력
                            var fd = new FormData(document.forms.namedItem("calendar-update"))
                            fd.append("no", eventObj.id);
                            fd.append("joinMemb.group.no", gno)
                            
                            fetch("/calendar/update", {
                                method: "POST",
                                body: new URLSearchParams(fd)
                            })
                            .then(function(response) {
                                return response.json();
                            })
                            .then(function(result) {
                                if (result.status == "success") {
                                    window.alert("수정되었습니다.")
                                    window.location.href = "index.html?gno=" + gno;
                                } else {
                                    window.alert("게시글 변경 실패");
                                    console.log(result.data);
                                    window.location.href = "index.html?gno=" + gno;
                                }
                            })
                        } // else 정상입력 END
                }) //UpdateBtn function END
            } //calendar/get success END
        }) //calendar/get END
    } //eventClick END                         

    ,customButtons: {
        addEventButton: { // 일정 추가 버튼 설정
            text : "일정 추가",  // 버튼 내용
            click : function(){ // 버튼 클릭 시 이벤트 추가
                $("#calendarModal").modal("show"); // modal 나타내기
                // console.log($("#calendarModal"));
                $("#x-addCalendar").on("click",function(){  // modal 추가 버튼 클릭 시
                    var startDt = $("#add-modal-startDt").val();
                    var endDt = $("#add-modal-endDt").val();
                    var content = $("#add-modal-content").val();
                    var xname = $("#add-modal-title").val();
                    // var xURL = $("#add-modal-URL").val();
                    
                    //내용 입력 여부 확인
                    if(content == null || content == ""){
                        alert("내용을 입력하세요.");
                    }else if(startDt == "" || endDt ==""){
                        alert("날짜를 입력하세요.");
                    }else if(new Date(endDt)- new Date(startDt) < 0){ // date 타입으로 변경 후 확인
                        alert("종료일이 시작일보다 먼저입니다.");
                    }else{ // 정상적인 입력 시
                        $.ajax ({
                                url: '/calendar/add',
                                type: 'POST',
                                data: {
                                    "joinMemb.group.no": gno,
                                    "name": $("#add-modal-title").val(),
                                    "content": content,
                                    "startDt": startDt,
                                    "endDt": endDt
                                }, //Data END
                                dataType: 'json',
                                    success: function(data) {
                                        console.log(data);
                                        if (data.status == "success") {
                                            alert("일정이 추가 되었습니다.");
                                            window.location.href = "index.html?gno=" + gno;
                                        } else {
                                            alert("일정 추가 실패");
                                            console.log(data);
                                            window.location.href = "index.html?gno=" + gno;
                                        }
                                    }, //success END
                                    error: function() {
                                        alert("에러발생");
                                    }// error END
                                }) //add ajax END                        
                    } //정상입력시 else문 END
                    // window.location.reload();
                }); // modal 추가 버튼 클릭 이벤트 END
            } // 버튼 클릭 시 이벤트 END
        },// addEventButton END
    }, //customButtons
    }); //var calendar End
    calendar.render(); // 달력 출력
  }); //document.addEventListener End
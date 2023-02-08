$(document).ready(function () {
  $('#header').load('/junho/mainHeader.html'); 
  $('#top').load('/ssang/groupBoard/none-search-top.html'); 
  $('#footer').load('/junho/mainfooter.html');
});

$("#listnone").hide();
let tbody = document.querySelector("#x-board-table tbody")
var trTemplate = document.querySelector("#tr-template");
var htmlGenerator = Handlebars.compile(trTemplate.innerHTML);

let pagenationUl = document.querySelector("#pagination-ul");
let pagetemplate = document.querySelector("#pageli-template");
let pageGernerator = Handlebars.compile(pagetemplate.innerHTML);

//form.html로 이동
var arr = location.href.split("?");
  if (arr.length == 1) {
    alert("요청 형식이 올바르지 않습니다.")
    throw "URL 형식 오류!";
  }
  var qs = arr[1];
  var params = new URLSearchParams(qs);
  var gno = params.get("gno");
  console.log("모임번호 : " + gno);

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
            $("#x-create-btn").hide();
          }//회원 등급 조회 if END 
        }//회원 등급 조회 success END           
      })//회원 등급 조회 ajax END 
    }//로그인 확인 else END
  }//로그인 여부 확인 success END
}); //로그인 여부 확인 ajax END  

//전체 리스트 불러오기 START
$.ajax({
  url : "/accounting/listbygroup", 
  type : "POST",
  async : false,
  dataType : "json",
  data : {
    "pageSize" : 10,
    "pageNo" : 1,
    "groupNo" : gno},
  success: function(result) {
    // console.log(result.data);
    var arr = result.data;
    // console.log(arr.accountingNo);
    var ing = "진행";
    var end = "완료";
    // console.log(arr.actCate);
    var updateData = []
    $.each(arr, function(index, arr) {
      updateData.push({
        gno : gno,
        no : arr.accountingNo,
        "actCate" : arr.actCate.cateName,
        title : arr.title,
        registDate : arr.registDate,
        statusno : arr.status,
        status : arr.status == 0 ? ing : end
      })
    })
    console.log(updateData);
    tbody.innerHTML = htmlGenerator(updateData);
    let totalListCount = result.totalListCount;
    let totalPageSize = result.totalPageSize;
    let requestPageNo = result.pageNo;

    if(totalListCount == 0) {
      $("#listnone").show();
    }

    if (totalPageSize <= 1) {
      $("#pagination-ul li").hide();
    } else { //페이지가 하나 이상이라면
      $("#pagination-ul li").show();

    //페이지네이션 번호 만들 2차원 배열 생성
    let totalpageArr = [];
    for (i = 1; i <= totalPageSize; i++) {
      totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
    }

    //페이지네이션 생성됨
    pagenationUl.innerHTML = pageGernerator(totalpageArr);
    
    //마지막 버튼에 값 넣기
    $(".pagination li:last").val(totalPageSize);

    //페이지네이션 클릭 이벤트
    $(".pagination li").on("click", function() {
      let clickli = $(this).attr('value');

      if (clickli == 1) {
      $(".pagination li:first").hide();
      } else {
        $(".pagination li:first").show();
      }

      if ( clickli == totalPageSize ) {
        $(".pagination li:last").hide();
      } else {
        $(".pagination li:last").show();
      }

      $.ajax({
        url : "/accounting/listbygroup",
        type : "POST",
        async : false,
        dataType : "json",
        data : {
          "pageSize" : 10,
          "pageNo" : clickli,
          "groupNo" : gno}, 
        success: function(result) {
          var arr = result.data;
          console.log(arr);
          var ing = "진행";
          var end = "완료";
          // console.log(arr.actCate);
          var updateData = []
          $.each(arr, function(index, arr) {
            updateData.push({
              "actCate" : arr.actCate.cateName,
              gno : gno,
              no : arr.accountingNo,
              title : arr.title,
              registDate : arr.registDate,
              statusno : arr.status,
              status : arr.status == 0 ? ing : end
            })
          })
          tbody.innerHTML = htmlGenerator(updateData);
        } //페이지네이션 클릭 후 list 요청 success END
      }) //페이지네이션 클릭 후 list 요청 ajax END
    }) //페이지네이션 클릭 Event END
    } //페이지가 하나 이상이라면 else END
  }//전체 리스트 불러오기 success END
}); //전체 리스트 불러오기 END

//selectbox 옵션별 목록 불러오기
$(document).on("click", "#selectCate", function() {
  let cate = $("#selectCate option:selected").val();
  console.log(cate);
  if (cate == 1) {
    $.ajax({
    url : "/accounting/listbygroup", 
    type : "POST",
    async : false,
    dataType : "json",
    data : {
      "pageSize" : 10,
      "pageNo" : 1,
      "groupNo" : gno}, 
    success: function(result) {
      var arr = result.data;
      // console.log(arr);
      var ing = "진행";
      var end = "완료";
      // console.log(arr.actCate);
      var updateData = []
      $.each(arr, function(index, arr) {
        updateData.push({
          "actCate" : arr.actCate.cateName,
          gno : gno,
          no : arr.accountingNo,
          title : arr.title,
          registDate : arr.registDate,
          statusno : arr.status,
          status : arr.status == 0 ? ing : end
        })
      })
      console.log(updateData);
      tbody.innerHTML = htmlGenerator(updateData);

      let totalListCount = result.totalListCount;
      let totalPageSize = result.totalPageSize;
      let requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#listnone").show();
      }

      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      } else { //페이지가 하나 이상이라면
        $("#pagination-ul li").show();

      //페이지네이션 번호 만들 2차원 배열 생성
      let totalpageArr = [];
      for (i = 1; i <= totalPageSize; i++) {
        totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
      }

      //페이지네이션 생성됨
      pagenationUl.innerHTML = pageGernerator(totalpageArr);
      
      //마지막 버튼에 값 넣기
      $(".pagination li:last").val(totalPageSize);

      //페이지네이션 클릭 이벤트
      $(".pagination li").on("click", function() {
        let clickli = $(this).attr('value');

        if (clickli == 1) {
        $(".pagination li:first").hide();
        } else {
          $(".pagination li:first").show();
        }

        if ( clickli == totalPageSize ) {
          $(".pagination li:last").hide();
        } else {
          $(".pagination li:last").show();
        }

        $.ajax({
          url : "/accounting/listbygroup",
          type : "POST",
          async : false,
          dataType : "json",
          data : {
            "pageSize" : 10,
            "pageNo" : clickli,
            "groupNo" : gno}, 
          success: function(result) {
            var arr = result.data;
            console.log(arr);
            var ing = "진행";
            var end = "완료";
            // console.log(arr.actCate);
            var updateData = []
            $.each(arr, function(index, arr) {
              updateData.push({
                "actCate" : arr.actCate.cateName,
                gno : gno,
                no : arr.accountingNo,
                title : arr.title,
                registDate : arr.registDate,
                statusno : arr.status,
                status : arr.status == 0 ? ing : end
              })
            })
            tbody.innerHTML = htmlGenerator(updateData);
          } //페이지네이션 클릭 후 list 요청 success END
        }) //페이지네이션 클릭 후 list 요청 ajax END
      }) //페이지네이션 클릭 Event END
      } //페이지가 하나 이상이라면 else END
    }//전체 리스트 불러오기 success END
  }); //전체 리스트 불러오기 END
  } else {
    $.ajax({
    url : "/accounting/listbygroup",
    type : "GET",
    async : false,
    dataType : "json",
    data : {
      "pageSize" : 10,
      "pageNo" : 1,
      "actCate" : cate,
      "groupNo" : gno},
    success: function(result) {
      var arr = result.data;
      // console.log(arr);
      var ing = "진행";
      var end = "완료";
      // console.log(arr.actCate);
      var updateData = []
      $.each(arr, function(index, arr) {
        updateData.push({
          "actCate" : arr.actCate.cateName,
          gno : gno,
          no : arr.accountingNo,
          title : arr.title,
          registDate : arr.registDate,
          statusno : arr.status,
          status : arr.status == 0 ? ing : end
        })
      });
      tbody.innerHTML = htmlGenerator(updateData);
      let totalListCount = result.totalListCount;
      let totalPageSize = result.totalPageSize;
      let requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#listnone").show();
      }

      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      } else { //페이지가 하나 이상이라면
        $("#pagination-ul li").show();

      //페이지네이션 번호 만들 2차원 배열 생성
      let totalpageArr = [];
      for (i = 1; i <= totalPageSize; i++) {
        totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
      }

      //페이지네이션 생성됨
      pagenationUl.innerHTML = pageGernerator(totalpageArr);
      
      //마지막 버튼에 값 넣기
      $(".pagination li:last").val(totalPageSize);

      //페이지네이션 클릭 이벤트
      $(".pagination li").on("click", function() {
        let clickli = $(this).attr('value');

        if (clickli == 1) {
        $(".pagination li:first").hide();
        } else {
          $(".pagination li:first").show();
        }

        if ( clickli == totalPageSize ) {
          $(".pagination li:last").hide();
        } else {
          $(".pagination li:last").show();
        }

        $.ajax({
          url : "/accounting/listbygroup",
          type : "POST",
          async : false,
          dataType : "json",
          data : {
            "pageSize" : 10,
            "pageNo" : clickli,
            "actCate" : cate,
            "groupNo" : gno},
          success: function(result) {
            var arr = result.data;
            // console.log(arr);
            var ing = "진행";
            var end = "완료";
            var updateData = []
            $.each(arr, function(index, arr) {
              updateData.push({
                "actCate" : arr.actCate.cateName,
                no : arr.accountingNo,
                title : arr.title,
                registDate : arr.registDate,
                statusno : arr.status,
                status : arr.status == 0 ? ing : end
              })
            })
            tbody.innerHTML = htmlGenerator(updateData);
          } //페이지네이션 클릭 후 list 요청 success END
        }) //페이지네이션 클릭 후 list 요청 ajax END
      }) //페이지네이션 클릭 Event END
      } //페이지가 하나 이상이라면 else END
    }//전체 리스트 불러오기 success END
  }); //전체 리스트 불러오기 END
  } //else 카테고리 선택 끝
})// selectbox change event END  

document.querySelector("#x-create-btn").onclick = function(){
  location.href="form.html?gno=" + gno;
};
let pagenationUl = document.querySelector("#pagination-ul");
let pagetemplate = document.querySelector("#pageli-template");
let pageGernerator = Handlebars.compile(pagetemplate.innerHTML);

$(document).ready(function () {
  $('#header').load('/junho/mainHeader.html'); //헤더 인클루드
  $('#footer').load('/junho/mainfooter.html'); //푸터 인클루드

  $.ajax({ //로그인 여부 확인 ajax START 비회원은 등록버튼 감춘다.
    url : "/member/getLoginUser",
    type : "POST",
    datatype : "json",
    success : function(result) {
      if (result.status == "fail") {
        $("#x-create-btn").hide(); 
      } 
    }
  }); //로그인 여부 확인 ajax END

  $("#listnone").hide();
  $("#searchnone").hide();
});//document.ready() END


//시,도 카테고리 불러오기
let selectSiList = document.querySelector("#nameSi");
let selectSiOption = document.querySelector("#optionSi-template");
let siopGernerator = Handlebars.compile(selectSiOption.innerHTML);
$.ajax({
  url : "/activeLocal/silistcate",
  type : "POST",
  async : false,
  success: function(result) {
      // console.log(result.data);
      $("#searchnone").hide();
      selectSiList.innerHTML = siopGernerator(result.data);
  }
});
//시,도 카테고리 불러오기 END

//군,구 카테고리 불러오기
let selectGuList = document.querySelector("#nameGu");
let selectGuOption = document.querySelector("#optionGu-template");
let guopGernerator = Handlebars.compile(selectGuOption.innerHTML);
$(document).on("click", "#nameSi", function() {
  // console.log("바뀜");
  $.ajax({
    url : "/activeLocal/gulistcate",
    type : "POST",
    async : false,
    data: {nameSi: $("#nameSi option:selected").val()},
    success: function(result) {
        // console.log(result.data);
        selectGuList.innerHTML = guopGernerator(result.data);
    }
  }); //ajax END
}); //nameSi change End
//군,구 카테고리 카테고리 불러오기 END

//카테고리에서 시 선택하면 게시글 리스트 불러오기 - 전체이거나 시를 선택하거나
let tbody = document.querySelector("#x-board-table tbody");
let trTemplate = document.querySelector("#tr-template");  
let htmlGenerator = Handlebars.compile(trTemplate.innerHTML);

//전체 리스트 불러오기 START
$.ajax({
  url : "/pickme/list",
  type : "POST",
  datatype : "json",
  data : {"pageSize" : 10,
          "pageNo" : 1},
  success : function(result) {
    // console.log(result);
    $("#searchInput").val("");
    tbody.innerHTML = htmlGenerator(result.data);    
    totalListCount = result.totalListCount;
    totalPageSize = result.totalPageSize;
    requestPageNo = result.pageNo;

    if(totalListCount == 0) {
      $("#listnone").show();
    } else {
      $("#listnone").hide();
      $("#searchnone").hide();
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
      
      //페이지네이션 ul 밑에 있는 버튼들 모두 찾음
      var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
      var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
      var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

      $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

      $(pagenationBtn).on("click", function() { //pagenationBtn click event START
        requestPageNo = $(this).attr("value");
        
        //요청 페이지 번호가 1번이라면 첫번째 버튼을 disabled or hide 설정한다. 
        if( requestPageNo == 1) {
          $("#pagination-ul .page-item .page-first-link").hide();
        } else {
          $("#pagination-ul .page-item .page-first-link").show();
        }

        //요청 페이지 번호가 마지막이라면 마지막 버튼을 disabled or hide 설정한다. 
        if ( requestPageNo == totalPageSize ) {
          $("#pagination-ul .page-item .page-end-link").hide();
        } else {
          $("#pagination-ul .page-item .page-end-link").show();
        }

        $.ajax({
          url : "/pickme/list",
          type : "POST",
          datatype : "json",
          data : {"pageSize" : 10,
                  "pageNo" : requestPageNo},
          success : function(result) {
            // console.log(result);
            tbody.innerHTML = htmlGenerator(result.data);
          }//success END
        })//list요청 ajax END
      })//pagenationBtn click event END
    } //totalpage가 1개 이상일면 else END
  } //전체 목록 불러오기 success END
})//전체 리스트 불러오기 END
//전체 리스트 불러오기 END

//시별 리스트 불러오기 START
$("#nameSi").on("change", function() {
  $("#listnone").hide();
  $("#searchnone").hide();
  if ($("#nameSi option:selected").val() == 0) { //if 시 카테고리가 전체 일 때 
    // console.log("시 전체 클릭");
    $.ajax({
      url : "/pickme/list",
      type : "POST",
      datatype : "json",
      data : {"pageSize" : 10,
              "pageNo" : 1},
      success : function(result) {
        // console.log(result);
        $("#searchInput").val("");
        tbody.innerHTML = htmlGenerator(result.data);

        totalListCount = result.totalListCount;
        totalPageSize = result.totalPageSize;
        requestPageNo = result.pageNo;

        if(totalListCount == 0) {
          $("#listnone").show();
        } else {
          $("#listnone").hide();
          $("#searchnone").hide();
        }

        if (totalPageSize < 1) {
          $("#pagination-ul li").hide();      
        } else {
          $("#pagination-ul li").show();

          //페이지네이션 번호 만들 2차원 배열 생성
          let totalpageArr = [];
          for (i = 1; i <= totalPageSize; i++) {
            totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
          }
          // console.log(totalpageArr);

          //페이지네이션 생성됨
          pagenationUl.innerHTML = pageGernerator(totalpageArr);
          
          //페이지네이션 ul 밑에 있는 버튼들 모두 찾음
          var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
          var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
          var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

          $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

          $(pagenationBtn).on("click", function() { //pagenationBtn click event START
            requestPageNo = $(this).attr("value");

          if( requestPageNo == 1) {
            $("#pagination-ul .page-item .page-first-link").hide();
          } else {
            $("#pagination-ul .page-item .page-first-link").show();
          }

          if ( requestPageNo == totalPageSize ) {
            $("#pagination-ul .page-item .page-end-link").hide();
          } else {
            $("#pagination-ul .page-item .page-end-link").show();
          }

            $.ajax({
              url : "/pickme/list",
              type : "POST",
              datatype : "json",
              data : {"pageSize" : 10,
                      "pageNo" : requestPageNo},
              success : function(result) {
                console.log(result);
                tbody.innerHTML = htmlGenerator(result.data);
              }//success END
            })//list요청 ajax END
          })//pagenationBtn click event END
        }
      } //전체 목록 불러오기 success END
    })//전체 리스트 불러오기 END  
  
  } else {
    $("#listnone").hide();
    $("#searchnone").hide();
    $.ajax({
    url : "/pickme/list",
    type : "POST",
    datatype : "json",
    data : {"pageSize" : 10,
            "pageNo" : 1,
            "nameSi" : $("#nameSi option:selected").val()},
    success : function(result) {
      tbody.innerHTML = htmlGenerator(result.data);      
      totalListCount = result.totalListCount;
      totalPageSize = result.totalPageSize;
      requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#listnone").show();
      }

      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      } else {
        $("#pagination-ul li").show();
        
        let totalpageArr = [];
        for (i = 1; i <= totalPageSize; i++) {
          totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
        }

        //페이지네이션 생성됨
        pagenationUl.innerHTML = pageGernerator(totalpageArr);
        
        //페이지네이션 ul 밑에 있는 버튼들 모두 찾음
        var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
        var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
        var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

        $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

        $(pagenationBtn).on("click", function() { //pagenationBtn click event START
          // console.log("클릭");

          requestPageNo = $(this).attr("value");

        //요청 페이지 번호가 1번이라면 첫번째 버튼을 disabled or hide 설정한다. 
        if( requestPageNo == 1) {
          $("#pagination-ul .page-item .page-first-link").hide();
        } else {
          $("#pagination-ul .page-item .page-first-link").show();
        }

        //요청 페이지 번호가 마지막이라면 마지막 버튼을 disabled or hide 설정한다. 
        if ( requestPageNo == totalPageSize ) {
          $("#pagination-ul .page-item .page-end-link").hide();
        } else {
          $("#pagination-ul .page-item .page-end-link").show();
        }

          $.ajax({
            url : "/pickme/list",
            type : "POST",
            datatype : "json",
            data : {"pageSize" : 10,
                    "pageNo" : requestPageNo,
                    "nameSi" : $("#nameSi option:selected").val()},
            success : function(result) {
              console.log(result);
              tbody.innerHTML = htmlGenerator(result.data);
            }//success END
          })//list요청 ajax END
        })//pagenationBtn click event END
      } //페이지수가 1개 이하 else END
    } //시별 목록 불러오기 success END
  })//시별 목록 불러오기 END
  } //시 카테고리에서 특정 시를 클릭 했을 때 END
}); //시 카테고리 클릭 이벤트 END
//시별 리스트 불러오기 END

//구별 리스트 불러오기 START
$("#nameGu").on("change", function() {

  $.ajax({
    url : "/pickme/list",
    type : "POST",
    datatype : "json",
    data : {"pageSize" : 10,
            "pageNo" : 1,
            "nameSi" : $("#nameSi option:selected").val(),
            "nameGu" : $("#nameGu option:selected").val()},
    success : function(result) {
      tbody.innerHTML = htmlGenerator(result.data);
      
      totalListCount = result.totalListCount;
      totalPageSize = result.totalPageSize;
      requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#listnone").show();
      } else {
        $("#listnone").hide();
        $("#searchnone").hide();
      }

      // console.log("시 카테고리 페이지 개수" + totalPageSize);
      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      
      } else {
        //페이지네이션 번호 만들 2차원 배열 생성
        let totalpageArr = [];
        for (i = 1; i <= totalPageSize; i++) {
          totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
        }

        //페이지네이션 생성됨
        pagenationUl.innerHTML = pageGernerator(totalpageArr);
        
        //페이지네이션 ul 밑에 있는 버튼들 모두 찾음
        var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
        var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
        var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

        $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

        $(pagenationBtn).on("click", function() { //pagenationBtn click event START
          // console.log("클릭");

          requestPageNo = $(this).attr("value");

        if( requestPageNo == 1) {
          $("#pagination-ul .page-item .page-first-link").hide();
        } else {
          $("#pagination-ul .page-item .page-first-link").show();
        }

        if ( requestPageNo == totalPageSize ) {
          $("#pagination-ul .page-item .page-end-link").hide();
        } else {
          $("#pagination-ul .page-item .page-end-link").show();
        }

          $.ajax({
            url : "/pickme/list",
            type : "POST",
            datatype : "json",
            data : {"pageSize" : 10,
                    "pageNo" : requestPageNo,
                    "nameSi" : $("#nameSi option:selected").val(),
                    "nameGu" : $("#nameGu option:selected").val()},
            success : function(result) {
              console.log(result);
              tbody.innerHTML = htmlGenerator(result.data);
            }//success END
          })//list요청 ajax END
        })//pagenationBtn click event END
      } //페이지수가 1개 이하 else END
    } //전체 목록 불러오기 success END
  })//전체 리스트 불러오기 END
})//구 카테고리 클릭 event END
//구별 리스트 불러오기 END

//검색 버튼
$("#searchBtn").on("click", function() {
  $("#listnone").hide();
  
  if ($("#searchInput").val() == 0) {
    window.alert("검색어를 입력해주세요")
  }

  if ($("#nameSi option:selected").val() == 0) {
    console.log("1. 시를 선택하지 않음");
    $.ajax({
    url : "/pickme/list",
    type : "POST",
    datatype : "json",
    data : {"pageSize" : 10,
            "pageNo" : 1,
            "keyword" : $("#searchInput").val()},
    success : function(result) {
      tbody.innerHTML = htmlGenerator(result.data);

      totalListCount = result.totalListCount;
      totalPageSize = result.totalPageSize;
      requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#searchnone").show();
      } else {
        $("#searchnone").hide();
      }

      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      } else { //페이지가 하나 이상이라면
        $("#pagination-ul li").show();

        let totalpageArr = [];
        for (i = 1; i <= totalPageSize; i++) {
          totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
        }
        //페이지네이션 생성됨
        pagenationUl.innerHTML = pageGernerator(totalpageArr);
        
        //페이지네이션 ul 밑에 있는 버튼들 모두 찾음
        var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
        var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
        var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

        $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

        $(pagenationBtn).on("click", function() { //pagenationBtn click event START
          requestPageNo = $(this).attr("value");
          
          if( requestPageNo == 1) {
            $("#pagination-ul .page-item .page-first-link").hide();
          } else {
            $("#pagination-ul .page-item .page-first-link").show();
          }

          if ( requestPageNo == totalPageSize ) {
            $("#pagination-ul .page-item .page-end-link").hide();
          } else {
            $("#pagination-ul .page-item .page-end-link").show();
          }

          $.ajax({
            url : "/pickme/list",
            type : "POST",
            datatype : "json",
            data : {"pageSize" : 10,
                    "pageNo" : requestPageNo,
                    "keyword" : $("#searchInput").val()},
            success : function(result) {
              console.log(result);
              tbody.innerHTML = htmlGenerator(result.data);
            }//success END
          })//list요청 ajax END
        })//pagenationBtn click event END
      } //totalpage가 1개 이상일면 else END
      } //success END
    })

  } else if ($("#nameSi option:selected").val() != 0 && $("#nameGu option:selected").val() == "군구 선택") {
    console.log("2. 시를 선택함");
    $.ajax({
    url : "/pickme/list",
    type : "POST",
    datatype : "json",
    data : {"pageSize" : 10,
            "pageNo" : 1,
            "nameSi" : $("#nameSi option:selected").val(),
            "keyword" : $("#searchInput").val()},
    success : function(result) {
      console.log(result);
      tbody.innerHTML = htmlGenerator(result.data);

      totalListCount = result.totalListCount;
      totalPageSize = result.totalPageSize;
      requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#searchnone").show();
      } else {
        $("#searchnone").hide();
      }

      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      } else { //페이지가 하나 이상이라면
        $("#pagination-ul li").show();

        let totalpageArr = [];
        for (i = 1; i <= totalPageSize; i++) {
          totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
        }
        //페이지네이션 생성됨
        pagenationUl.innerHTML = pageGernerator(totalpageArr);
        
        var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
        var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
        var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

        $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

        $(pagenationBtn).on("click", function() { //pagenationBtn click event START
          requestPageNo = $(this).attr("value");
          
          //요청 페이지 번호가 1번이라면 첫번째 버튼을 disabled or hide 설정한다. 
          if( requestPageNo == 1) {
            $("#pagination-ul .page-item .page-first-link").hide();
          } else {
            $("#pagination-ul .page-item .page-first-link").show();
          }

          //요청 페이지 번호가 마지막이라면 마지막 버튼을 disabled or hide 설정한다. 
          if ( requestPageNo == totalPageSize ) {
            $("#pagination-ul .page-item .page-end-link").hide();
          } else {
            $("#pagination-ul .page-item .page-end-link").show();
          }

          $.ajax({
            url : "/pickme/list",
            type : "POST",
            datatype : "json",
            data : {"pageSize" : 10,
                    "pageNo" : requestPageNo,
                    "nameSi" : $("#nameSi option:selected").val(),
                    "keyword" : $("#searchInput").val()},
            success : function(result) {
              console.log(result);
              tbody.innerHTML = htmlGenerator(result.data);
            }//success END
          })//list요청 ajax END
        })//pagenationBtn click event END
      } //totalpage가 1개 이상일면 else END
      } //success END
    })

  } else if ($("#nameSi option:selected").val() != 0 && $("#nameGu option:selected").val() != "군구 선택") {
    console.log("3. 군구를 선택함");

    $.ajax({
    url : "/pickme/list",
    type : "POST",
    datatype : "json",
    data : {"pageSize" : 10,
            "pageNo" : 1,
            "nameGu" : $("#nameGu option:selected").val(),
            "keyword" : $("#searchInput").val()},
    success : function(result) {
      console.log(result);
      tbody.innerHTML = htmlGenerator(result.data);

      totalListCount = result.totalListCount;
      totalPageSize = result.totalPageSize;
      requestPageNo = result.pageNo;

      if(totalListCount == 0) {
        $("#searchnone").show();
      } else {
        $("#searchnone").hide();
      }

      if (totalPageSize <= 1) {
        $("#pagination-ul li").hide();
      } else { //페이지가 하나 이상이라면
        $("#pagination-ul li").show();

        let totalpageArr = [];
        for (i = 1; i <= totalPageSize; i++) {
          totalpageArr.push({"no" : i, "totalPageSize" : `${i}`})            
        }
        pagenationUl.innerHTML = pageGernerator(totalpageArr);
        
        var pagenationBtn = document.querySelectorAll("#pagination-ul .page-item #page-Btn");
        var pagenationFirstBtn = document.querySelector("#pagination-ul .page-item .page-first-link")
        var pagenationLastBtn = document.querySelector("#pagination-ul .page-item .page-end-link")

        $(pagenationLastBtn).val(totalPageSize); //마지막 버튼에 값 넣기

        $(pagenationBtn).on("click", function() { //pagenationBtn click event START
          requestPageNo = $(this).attr("value");
          
          if( requestPageNo == 1) {
            $("#pagination-ul .page-item .page-first-link").hide();
          } else {
            $("#pagination-ul .page-item .page-first-link").show();
          }

          if ( requestPageNo == totalPageSize ) {
            $("#pagination-ul .page-item .page-end-link").hide();
          } else {
            $("#pagination-ul .page-item .page-end-link").show();
          }

          $.ajax({
            url : "/pickme/list",
            type : "POST",
            datatype : "json",
            data : {"pageSize" : 10,
                    "pageNo" : requestPageNo,
                    "nameGu" : $("#nameGu option:selected").val(),
                    "keyword" : $("#searchInput").val()},
            success : function(result) {
              console.log(result);
              tbody.innerHTML = htmlGenerator(result.data);
            }//success END
          })//list요청 ajax END
        })//pagenationBtn click event END
      } //totalpage가 1개 이상일면 else END
      } //success END
    })
  }
})

document.querySelector("#x-create-btn").onclick = function(){
  location.href="form.html";
};
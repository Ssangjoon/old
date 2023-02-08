//시,도 카테고리 카테고리 불러오기 START
let selectSiList = document.querySelector("#nameSi");
let selectSiOption = document.querySelector("#optionSi-template");
let opGernerator = Handlebars.compile(selectSiOption.innerHTML);

$.ajax({
  url : "/activeLocal/silistcate",
  type : "POST",
  async : false,
  success: function(result) {
      selectSiList.innerHTML = opGernerator(result.data);
  }
});
// 시,도 카테고리 불러오기 END

//군,구 카테고리 불러오기 START
let selectGuList = document.querySelector("#nameGu");
let selectGuOption = document.querySelector("#optionGu-template");
opGernerator = Handlebars.compile(selectGuOption.innerHTML);

$(document).on("click", "#nameSi", function() {
  $.ajax({
    url : "/activeLocal/gulistcate",
    type : "POST",
    async : false,
    data: {nameSi: $("#nameSi option:selected").val()},
    success: function(result) {
        console.log(result.data);
        selectGuList.innerHTML = opGernerator(result.data);
    }
  }); //ajax END
}); //nameSi change End
//군,구 카테고리 카테고리 불러오기 END

//관심 활동 리스트 불러오기 START
let selectPuposeList = document.querySelector("#purpose");
let selectPurposeOption = document.querySelector("#optionPurpose-template");
let PurposeopGernerator = Handlebars.compile(selectPurposeOption.innerHTML);

$.ajax({
    url : "/purpose/list",
    type : "POST",
    async : false,
    datatype : "json",
    success : function(result) {
      console.log(result);
      selectPuposeList.innerHTML = PurposeopGernerator(result);
    }//success END
  })//ajax END
// 관심 활동 리스트 불러오기 END



//서버로 보내기
$("#x-add-btn").on("click", function() { //등록버튼 click Event START
let xTitle = document.querySelector("input[name=title]");
let xActiveLocal = $("#nameGu option:selected").val();
let xContent = document.querySelector("#content");
let xPurpose = $("#purpose option:selected").val();;

  if (xTitle.value == "" ||
      xActiveLocal == 0 ||
      $("#nameSi").val() == 0 ||
      $("#nameGu").val() == 0 ||
      xContent.value == "" ||
      xPurpose == 0) 
      {
        window.alert("필수 입력 항목이 비어 있습니다.");
        return;
  }// 필수 입력 항목 검사 if END

let fd = new FormData(document.forms.namedItem("pickme-form"))

fetch("/pickme/add", {
  method: "POST",
  body: new URLSearchParams(fd)
  })
  .then(function(response){
    return response.json();
  })
  .then(function(result){
    console.log(result);
    if (result.status == "success") {
      window.alert("등록되었습니다.");
      location.href = "index.html";
    } else {
      window.alert("게시글 등록 실패!");
      console.log(result.data);
    }
  });
})//("#x-add-btn").onclick END

//취소버튼
document.querySelector("#x-cancel-btn").onclick = function() {
  window.location.href = "index.html";
};

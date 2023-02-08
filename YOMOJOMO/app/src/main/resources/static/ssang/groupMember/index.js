import{getGroupNO,getLoginUser,findgrouplistByGno} from '/ssang/js/module.js';
var getGroup = getGroupNO();//쿼리스트링에서 가져온 모임번호 
var loginUser = await getLoginUser() //로그인한 유저의 정보
var groupList = await findgrouplistByGno(getGroup) // 그룹번호로 조회한 모임과 회원정보 
var membContainer = document.querySelector("#membContainer");
var membTemplate = document.querySelector('#memb-template') 
var membGenerator = Handlebars.compile(membTemplate.innerHTML);
$('.memb-count').text(`회원수 : ${groupList.data.length} 명`);
for (var list of groupList.data){
    console.log(list)
    if(list.memberGrade.gradeName == '모임장'){
        $('.x-memb-name').text(list.member.memberName)
    }
}
membContainer.innerHTML= membGenerator(groupList.data)

// 멤버 신고 
var xTitle = document.querySelector('#recipient-name');
var xContent =document.querySelector('#message-text');
$(document).on("click", ".memb-report", function() {
  var value = $(this).val();
  console.log(value)
  $(document).on("click", "#memb-report-exactly", function() {
    console.log('클릭')
    if(xTitle.value == "" || xContent.value == ""){
      alert("신고사유를 작성해주세요");
      return;
    }
    var fd = new FormData(document.forms.namedItem("form1"));
    fd.append('reported',value)
    fd.append('rptCateNo',1)
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
  })
})///멤버 신고 끝
// function searchMember() {
//   // var groupListItems = document.querySelectorAll('.list-group-item');
//   // groupListItems.forEach((item) => {
//   //   item.classList.add('list-search')
//   // })
//   var searchValue = document.querySelector('.member-top-search .form-control').value.toUpperCase();
//   console.log(searchValue)

//   var name;
//   var items = document.querySelectorAll('.list-group-item-search');
//   console.log(items)
//   for(var i=0;i<items.length;i++){
//     name = items[i].getElementsByClassName("x-memb-name");
//     console.log(name)
//     if(name[0].innerHTML.toUpperCase().indexOf(searchValue) > -1){
//       items[i].style.display = "flex";
//     }else{
//       items[i].style.display = "none";
//     }
//   }
// }
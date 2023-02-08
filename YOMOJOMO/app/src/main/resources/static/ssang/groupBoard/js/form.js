import{getGroupNO,getLoginUser} from '/ssang/js/module.js';
var getGroup = getGroupNO();
var loginUser = await getLoginUser() //로그인한 유저의 정보
console.log(loginUser.data)
$('.written-author-name').text(loginUser.data.memberName);
// ============================================게시글 입력
$('#s-post-btn').on('click', () => { 
  var xContent = document.getElementById("xx-content")
  if (xContent.value == ""){
    Swal.fire({
      icon: 'warning',
      title: '잠시만요',
      text: '작성된 내용이 없어요',
      footer: ''
    })
    return;
  }
  var formData = new FormData();
  formData.append('content',xContent.value)
  formData.append('groupNo',getGroup)
  fetch('/board/add',{
    method:"post",
    body: formData
  })
  .then(res => res.json())
  .then(res => {
    console.log(res.status)
    location.href=`index.html?gno=${getGroup}`
  });
})
  document.querySelector("#s-cancel-btn").onclick = function() {
    window.location.href = `index.html?gno=${getGroup}`;
  };
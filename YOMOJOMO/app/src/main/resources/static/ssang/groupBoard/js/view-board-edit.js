import{getGroupNO,getBoardNO} from '/ssang/js/module.js';
var getGroup = getGroupNO();
var getBoard = getBoardNO();
//=============================================게시글 불러오기 
fetch(`/board/get?no=${getBoard}`)
.then(function(response) {
  return response.json();
})
.then(function(result){
  console.log(result.data)
  $('.written-content').text(result.data.content)
  $('.written-author-name').text(result.data.writer.memberName)
  $('.written-createdTime').text(result.data.registDate)
})
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
  formData.append('no',getBoard)
  formData.append('groupNo',getGroup)
  fetch('/board/update',{
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
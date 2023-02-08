export function getGroupNO(){
  // 1) URL에서 쿼리스트링(query string)을 추출한다.
  var arr = location.href.split("?");
  // console.log(arr);
  
  if (arr.length == 1) {
    alert("요청 형식이 옳바르지 않습니다.")
    throw "URL 형식 오류!";
  }
  
  var qs = arr[1];
  // console.log(qs);
  
  // 2) 쿼리 스트링에서 email 값을 추출한다.
  var params = new URLSearchParams(qs);
  var gno = params.get("gno");
  
  if (gno == null) {
    alert("모임 번호가 없습니다.");
    throw "파라미터 오류!";
  }
  //console.log(no);
  return gno;
}

export function getBoardNO(){
  // 1) URL에서 쿼리스트링(query string)을 추출한다.
  var arr = location.href.split("?");
  // console.log(arr);
  
  if (arr.length == 1) {
    alert("요청 형식이 옳바르지 않습니다.")
    throw "URL 형식 오류!";
  }
  
  var qs = arr[1];
  // console.log(qs);
  
  // 2) 쿼리 스트링에서 email 값을 추출한다.
  var params = new URLSearchParams(qs);
  var bno = params.get("bno");
  
  if (bno == null) {
    alert("모임 번호가 없습니다.");
    throw "파라미터 오류!";
  }
  //console.log(no);
  return bno;
}
export async function findgrouplistByGno(no){
  const response = await fetch(`/joinmember/grouplistbygno?group.no=${no}`)
  .then(function(res){return res.json()})
  return response
}
export async function getLoginUser(){
  const response = await fetch("/member/getLoginUser")
  .then(function(res){return res.json()})
   return response;                     
}
export async function qCount(no){
  const response = await fetch(`/applyQuestion/count?no=${no}`)
  .then(function(res){return res.json()})
  return response;
}
export async function findgrouplistByMno(no){
  const response = await fetch(`/joinmember/grouplistbymno?member.no=${no}`)
  .then(function(res){return res.json()})
  return response;
}
export async function findGrade(no){
  const response = await fetch(`/joinmember/findGrade?group.no=${no}`)
  .then(function(res){return res.json()})
  return response;
}
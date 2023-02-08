var xName = document.querySelector("input[name=name]");
var xTel = document.querySelector("input[name=tel]");

document.querySelector("form[name=findid]").onsubmit = function() {
  if ( xTel.value == "") {
    window.alert("필수 입력 항목이 비어 있습니다.");
    return false;
  }
  
  var fd = new FormData(document.forms.namedItem("findid"));
  
  fetch("/member/findid", { 
      method: "POST",
      body: new URLSearchParams(fd)
    }) 
    .then(function(response) {
      return response.text();
    })
    .then(function(text) {
      if (text != 0) {
			$('.modal').show();	
			$(".email").append(text)

		$('.close').on('click', function() {
			$('.modal').hide();
			location.reload();
		});
		$('.btn-close').on('click', function() {
			$('.modal').hide();
			location.reload();
		});
		$(window).on('click', function() {
			if (event.target == $('.modal').get(0)) {
				$('.modal').hide();
				location.reload();
			}
		});
      } else {
    	  window.alert("없는 사용자거나 탈퇴한 사용자입니다.")
      }
    });
  return false;
};


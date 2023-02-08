var boardCardDiv = document.querySelector(".announcement_tr")
// const lightBtn = document.querySelector(".category");

function allList() {
  fetch("/notice/list")
    .then(function(response) {
      return response.json()
    })
    .then(function(boards) {
      for (var i = 0; i < boards.length; i++) {
        var tr = document.createElement("tr")
        // div.classList.add("card-body")
        tr.innerHTML = 
        `
        <td>${boards[i].no}</td><td style="display:none">${boards[i].cate}</td>
        <td><a href="announcement_view.html?no=${boards[i].no}">${boards[i].title}</td></a>
        <td>${boards[i].regDate}</td>
        `
        boardCardDiv.appendChild(tr)
      }
    })
}


  

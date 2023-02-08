var boardCardDiv = document.querySelector(".faq_tr")
// const lightBtn = document.querySelector(".category");

function allList() {
  fetch("/faq/list")
    .then(function(response) {
      return response.json()
    })
    .then(function(boards) {
      for (var i = 0; i < 3; i++) {
        var tr = document.createElement("tr")
        // div.classList.add("card-body")
        tr.innerHTML = `
        <td style="display:none;">${boards[i].no}</td>
        <td style="font-weight:bold;">업데이트</td>
        <td><a href="faq_view.html?no=${boards[i].no}">
        ${boards[i].title}</td></a>
        `
        boardCardDiv.appendChild(tr)
      }
    })
}


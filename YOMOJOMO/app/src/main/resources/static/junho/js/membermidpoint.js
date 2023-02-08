$(document).ready(function () {
  $('#header').load('/junho/mainHeader.html'); //헤더 인클루드
  $('#footer').load('/junho/mainfooter.html'); //푸터부분 인클루드
});
const url = new URL(window.location.href)
const urlParams = url.searchParams
console.log(url)
console.log(urlParams)
var gNoParameter = urlParams.get('gno')
var calNoParaMeter = urlParams.get('cal_no')
var initialLat = 126.9784147;
var initialLng = 37.5666805;

getDataListFromDb();

// Main Map
var mapContainer1 = document.getElementById('map1'), // 지도를 표시할 div 
    mapOption1 = { 
        center: new kakao.maps.LatLng(initialLng, initialLat), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨
    };

var map1 = new kakao.maps.Map(mapContainer1, mapOption1); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();


// Modal Map

// 페이지 별 장소 저장
var pointer = null;
// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(initialLng, initialLat), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});


// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);
        savePointer(data);

        // 페이지별 데이터 담기
        pointer = data;
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span class="oldaddr">' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span class="oldaddr">' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item itme'+ index;

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

// 확정된 위치를 담을 배열
var finalPoint = []
// modal 에서 장소 선택하기
var selectDestinationEl = document.querySelector('.select-destination');

// 장소 객체정보를 담는곳
var placeDataList = [];

// 카테고리에서 뽑아낸 장소 저장하는 함수
function savePointer(places) {
    var itemEls = document.querySelectorAll('.item');
    var targetItem = null; 
    var selectNo;
    console.log(itemEls)
    console.log(places)
    itemEls.forEach((item) => {
        item.addEventListener('click', function(e) {
            
            console.log(e.target)
            console.log(e.target.parentNode)
            console.log(e.target.parentNode.tagName)
            
            if (e.target.parentNode.tagName == 'DIV') {
                targetItem = e.target.parentNode.parentNode;
            } else if (e.target.parentNode.tagName == "LI") {
                targetItem = e.target.parentNode;
            }
            var selectAddr = targetItem.querySelector('.oldaddr');
            selectNo = parseInt(targetItem.className.split(' ')[1].substr(-1))
            console.log(selectAddr.innerHTML)
            selectDestinationEl.innerHTML = selectAddr.innerHTML
            console.log(places[selectNo])
            
            var saveBtnEl = document.querySelector('.btn-save')
            saveBtnEl.addEventListener('click', function() {
                placeDataList[modalclickPoint] = (places[selectNo])
                console.log(modalclickPoint)
                places=null;
                console.log(placeDataList)

            })
        })
    })
}




var finalDestination = [];
var finalDestinationLatLng = [];
var clickSaveBtn = 0;
var infowindowtests = [];
var modalclickPoint;
function modalclick(i) {
    var destinationItemEl = document.querySelector(`.destination-item${i}`);
    var nameItemEl = document.querySelector(`.member-name${i}`);
    var saveBtnEl = document.querySelector('.btn-save')
    modalclickPoint = i;
    
    saveBtnEl.addEventListener('click', function() {
        if (selectDestinationEl.innerHTML != '') {
            destinationItemEl.value = selectDestinationEl.innerHTML;
            finalDestination[clickSaveBtn++] = selectDestinationEl.innerHTML;
            selectDestinationEl.innerHTML = "";
            keyword.value = "";
            destinationItemEl = null;
            console.log(finalDestination)


        for (var i = 0; i < finalDestination.length; i ++) {
            if (!isDup(finalDestination)) {
            // 주소로 좌표를 검색합니다
                geocoder.addressSearch(finalDestination[i], function(result, status) {

                    // 정상적으로 검색이 완료됐으면 
                    if (status === kakao.maps.services.Status.OK) {
                
                        var coords1 = new kakao.maps.LatLng(result[0].y, result[0].x);
                        finalDestinationLatLng[i-1] = coords1;
                        // 결과값으로 받은 위치를 마커로 표시합니다
                        var marker1 = new kakao.maps.Marker({
                            map1: map1,
                            position: coords1
                        });
                        
                        
                
                        // 인포윈도우로 장소에 대한 설명을 표시합니다
                        var infowindow = new kakao.maps.InfoWindow({
                            content: '<div style="width:150px;text-align:center;padding:6px 0;">사용자</div>'
                        });
                        infowindow.open(map1, marker1);
                        infowindowtests.push(infowindow);
                
                        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                        map1.setCenter(coords1);
                    } 
                });
            } else {
                alert('주소가 중복됩니다.')
            }
        }  
            
        }
    })
    
}

function clicktest() {
    console.log(finalDestination)
}


// 배열 안에 중복 값 찾기
function isDup(arr)  {
    return arr.some(function(x) {
      return arr.indexOf(x) !== arr.lastIndexOf(x);
    });  
  }


var memberList = [];
var addrList = [];
var finalList = [];

var ulEL = document.querySelector(".member-list")
    
// 템플릿 엔진에서 사용할 HTML 조각을 가져오기
var liTemplate = document.querySelector("#li-template");
console.log(liTemplate)
//템플릿 엔진 준비
var htmlGenerator = Handlebars.compile(liTemplate.innerHTML);
console.log(htmlGenerator)




console.log(window.location.search)

function getDataListFromDb() {
    console.log(gNoParameter)
    console.log(calNoParaMeter)
    var data = {'group.no': gNoParameter, 'calendar.no': calNoParaMeter};
    $.ajax( {
        url: "/midpoint/member/calendar/list",
        type: "POST",
        dataType: 'json',
        data: data,
        success : function(result) {
            for (var i = 0; i < result.length; i++) {
                finalList[i] = {
                    memberName: result[i].member.memberName,
                    addr: result[i].addr,
                    lat: result[i].lat,
                    lng: result[i].lng
                }
                
            }
            console.log(finalList)
            ulEL.innerHTML = htmlGenerator(finalList);
            setTimeout(markerList(finalList),3000)

        }
    })
}


function markerList(finalList) {
    var positions =[]
    for (var j = 0; j <finalList.length ; j++) {
        if (finalList[j].lat != null) {
        var positiontest = {
            name: finalList[j].memberName,
            latlng: new kakao.maps.LatLng(Number(finalList[j].lng), Number(finalList[j].lat))
        }
        positions.push(positiontest)
        }
    }
    console.log(positions)       
    
    
    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
        
    for (var i = 0; i < positions.length; i ++) {
        
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35); 
        
        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
        
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map1, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image : markerImage // 마커 이미지 
        });
    }
}



function saveDataIndb(i) {
    console.log(placeDataList[i].x)
    var data = {'lat': placeDataList[i].x, 'lng': placeDataList[i].y, 'group.no': gNoParameter, 'calendar.no': calNoParaMeter, 'addr': placeDataList[i].address_name}
    
    console.log(data)
    $.ajax({
        url: "/midpoint/member/calendar/add",
        type: "POST",
        dataType: "json",
        data: data,
        success : function(result) {
            console.log(result);
        }
    })
    
}

var lat = 0;
var lng = 0;

function selectMidpoint() {
    console.log(finalList)
    for(var i = 0; i< finalList.length; i++) {
        lat += Number(finalList[i].lat)
        lng += Number(finalList[i].lng)
    }
    lat = lat / finalList.length
    lng = lng / finalList.length

    // removeMarkertests();
    
    // panTo(lat, lng)
    // midpointMarker(lng, lat)
    var deleteData ={'group.no': gNoParameter, 'calendar.no': calNoParaMeter}
    console.log(lng, lat)
    $.ajax({
        url: "/midpoint/member/calendar/delete",
        type: "POST",
        dataType: "json",
        data: deleteData,
        success : function(result) {
            console.log(result);
        }
    })
    window.location = `/junho/midpoint/memberfinalpoint.html?lng=${lng}&lat=${lat}&gno=${gNoParameter}&cal_no=${calNoParaMeter}`
}


setTimeout(function(){clickCheck()},1000);
function clickCheck() {
    console.log(finalList)
    for (var i = 0; i < finalList.length; i++) {
        if (finalList[i].addr != null) {
            document.querySelectorAll('.btn-save-data')[i].classList.add('btn-finish');
            document.querySelectorAll('.input-destination')[i].classList.add('input-finish');
            console.log('clickcheck')
        }
        var btnFinishEl = document.querySelectorAll('.btn-finish')[i]
        var inputFinishEl = document.querySelectorAll('.input-finish')[i]
        btnFinishEl.style.backgroundColor = '#333';
        btnFinishEl.innerHTML = '완료';
        btnFinishEl.disabled = true;
        inputFinishEl.style.backgroundColor = '#333';
        inputFinishEl.style.color = '#fff';
        inputFinishEl.disabled = true;

    }

}
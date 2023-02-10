/**
 * 
 */

function updateMemberFnc(event) {
	//modifyMember.do 사용자 정보 수정
	let tr = $(event.target).parent().parent(); //tr
	//console.log(tr.find('td:nth-of-type(5) input').val());
	//console.log(tr.children().eq(0).text());

	let id = tr.children().eq(0).text(); // element.value속성을 읽어옴.
	let name = tr.find('td:nth-of-type(2) input').val()
	let phone = tr.find('td:nth-of-type(3) input').val();
	let addr = tr.find('td:nth-of-type(4) input').val();
	let resp = tr.find('td:nth-of-type(5) input').val();

	$.ajax({
		url: 'modifyMember2.do',
		method: 'post',
		data: { id: id, name: name, phone: phone, addr: addr, resp: resp },
		success: function(result) {
			// 처리된 정보를 화면에 생성.
			console.log(result);
			if (result.retCode == 'Success') {

				tr.replaceWith(makeRow(result.member));
			} else {
				alert("입력미완!");
			}
		},
		error: function(reject) {
			console.log(reject);
		}
	})

}

function makeRow(member = {}) { /*멤버는 객체타입이다*/
	let tr = $('<tr />'); //document.createElement('tr');
	tr.on('dblclick', function(e) {

		let id = $(this).children().eq(0).text();
		let name = $(this).children().eq(1).text();
		let phone = $(this).children().eq(2).text();
		let addr = $(this).children().eq(3).text();
		let resp = $(this).children().eq(4).text();

		let nTr = $('<tr />').append(
			$('<td />').text(id),
			$('<td />').append($('<input class="name" />').val(name)),
			$('<td />').append($('<input class="phone"/>').val(phone)),
			$('<td />').append($('<input class="addr" />').val(addr)),
			$('<td />').append($('<input class="auth" />').val(resp)),
			$('<td />').append($('<button onclick="updateMemberFnc(event)">수정</button>'))
		)
		//새로운 tr로 기존 tr을대신
		nTr = $('#template tr').clone(true);
		nTr.find('input.name').val(name);
		nTr.find('input.name').val(name);
		
		tr.replaceWith(nTr);
	})
	tr.append(
		$('<td />').text(member.memberId),
		$('<td />').text(member.memberName),
		$('<td />').text(member.memberPhone),
		$('<td />').text(member.memberAddr),
		$('<td />').text(member.responsibility),
		$('<td />').append(
			$('<button>삭제</button>').attr('mid', member.memberId).on('click', deleteMemberFnc) /*함수에 ()적으면 안된다 (클릭할때 실행해야 하는데 바로실행해버리기때문)*/
		)

	);
	return tr;
}
function deleteMemberFnc(e) {

	if (!window.confirm("삭제하시겠습니까?")) {
		return;
	}

	let user = $(e.target).attr('mid');

	$.ajax({
		url: 'removeMember.do',
		data: { id: user }, //removeMember.do?id=user
		success: function(result) {
			if (result.retCode == 'Success') {
				$(e.target).parent().parent().remove();
			} else {
				alert('삭제오류');
			}
		},
		error: function(reject) {
			console.log(reject);
		}
	});

	//$(e.target).parent().parent().remove();
}

$(document).ready(function() {  //document가 다운로드 되서 준비가 되면 함수 실행 
	// (null요소에는 이벤트를 달 수 없으니까)

	let clone = $('#template').clone(true);
	//console.log(clone.find('tr'));
	let tr = clone.find('tr');
	//tr.find('.name').val('test');

	
	$('#list').append(tr);
	//목록가져오는 Ajax호출
	//console.log($('#list'));
	$.ajax({
		url: 'memberList.do',
		success: function(result) {
			console.log(result);
			$(result).each(function(idx, item) {
				$('#list').append(makeRow(item));
			})

		},
		error: function(reject) {
			console.log(reject);
		}
	});



	//등록이벤트
	$("#addBtn").on("click", function() {

		let id = $('#mid').val(); // element.value속성을 읽어옴.
		let name = $('#mname').val();
		let phone = $('#mphone').val();
		let addr = $('#maddr').val();
		let img = $('#mimage')[0].files[0];

		let formData = new FormData();
		formData.append('id', id);
		formData.append('name', name);
		formData.append('phone', phone);
		formData.append('addr', addr);
		formData.append('img', img);

		$.ajax({
			url: 'addMember.do',
			method: 'post',
			data: formData,
			contentType: false,
			processData: false,
			success: function(result) {
				// 처리된 정보를 화면에 생성.
				console.log(result);
				if (result.retCode == 'Success') {
					$('#list').append(makeRow(result.member));
				} else {
					alert("입력미완!");
				}
			},
			error: function(reject) {
				console.log(reject);
			}
		})

	})

});


/**
 * empList.js
 */
//목록출력하기.


let totalAry = []; // 전체목록 담아놓을 용도.
fetch("../empListJson")  //아작스 호출.
	.then((resolve) => resolve.json())   // 가져온 데이터를 제이슨으로 바꿔준다
	.then((result) => {
		//배열관련 메소드: forEach, map, filter, reduce 메소드.
		localStorage.setItem('total', result.length); // web페이지에서 정보를 담아놓는 곳?
		totalAry = result;
		/*result.forEach(function(item, idx, arry) {
			let tr = makeTr(item); //tr생성 후 반환.
			list.append(tr);
		});*/
		//showPages(5);
		//employeeList(5);
		document.querySelector("#pageCnt").dispatchEvent(chgEvent); //정의한 이벤트 실행할때 정의한 이벤트(dispatchEvent) 활용
	})
	.catch((reject) => {
		console.log(reject);
	});
//저장버튼 submit 이벤트 등록.
document.querySelector('form[name="empForm"]').addEventListener('submit', addMemberFnc);  // 폼 태그중에서 이름이 empForm인것을 가져오겠습니다.

//전체선택 체크박스.
document.querySelector('thead input[type="checkbox"]').addEventListener('change', allCheckChange);
//선택삭제 버튼.
document.querySelector('#delSelectedBtn').addEventListener('click', deletedCheckedFnc);

let chgEvent = new Event('change'); //새로고침해도 정상적으로 출력

// 데이터 한건을 활용해서 tr이라는 요쇼를 생성.
function makeTr(item) {  //매개값으로 받아오면 그 값을 가지고 tr을 만들어서 반환해준다   //item은 object{}타입
	//result베열에 들어있는 값의 갯수만큼
	//DOM요소생성.
	let titles = ["id", "lastName", "email", "hireDate", "job"];  // json이랑 이름 같도록.
	//데이터 건수만큼 반복
	let tr = document.createElement("tr");
	//colums의 갯수만큼 반복.
	titles.forEach(function(title) {
		let td = document.createElement("td");
		td.innerText = item[title];
		tr.append(td);
	});
	//삭제
	let td = document.createElement("td");
	let btn = document.createElement("button");
	btn.innerText = "삭제";
	btn.addEventListener("click", deleteRowFunc);   // click이벤트가 실행되면 deleteRowFunc이 실행.
	td.append(btn);
	tr.append(td);
	//tr.반환
	//수정
	td = document.createElement("td");
	btn = document.createElement("button");
	btn.innerText = "수정";
	btn.addEventListener("click", modifyTrFunc);
	td.append(btn);
	tr.append(td);
	//체크박스
	td = document.createElement("td");
	let chk = document.createElement('input');
	chk.setAttribute('type', 'checkbox');
	chk.addEventListener('change', countCheck);

	td.append(chk);
	tr.append(td);

	return tr;
}
// 전체선택 체크박스 - 개별체크박스 동기화.
function countCheck() {
	// 체크박스 수와 체크된 박스 수 비교.
	let check = document.querySelector('thead').children[0].children[7].children[0];
	let count = document.querySelectorAll('tbody input[type = "checkbox"]').length;
	let i = document.querySelectorAll('tbody input[type = "checkbox"]:cahecked').length;
	if (count == i) {
		check.checked = true;
	} else {
		check.checked = false;
	}
}

// 삭제버튼 이벤트 콜백함수.
function deleteRowFunc() {
	let id = this.parentElement.parentElement.firstChild.innerText;
	fetch("../empListJson?del_id=" + id, {
		method: "DELETE",
	})
		.then((resolve) => resolve.json())
		.then((result) => {
			console.log(result);
			if (result.retCode == "Success") {
				alert("정상적으로 삭제 되었습니다.");
				this.parentElement.parentElement.remove();
			} else if (result.retCode == "Fail") {
				alert("삭제 중 오류 발생");
			}
		})
		.catch((reject) => console.log(reject));
}
// 수정화면 함수.
function modifyTrFunc() {
	//this. => 수정
	let thisTr = this.parentElement.parentElement;
	console.log("사원번호 : ", thisTr.children[0].innerText);
	console.log("이름 : ", thisTr.children[1].innerText);
	let id = thisTr.children[0].innerText;
	let name = thisTr.children[1].innerText;
	let mail = thisTr.children[2].innerText;
	let hire = thisTr.children[3].innerText;
	let job = thisTr.children[4].innerText;
	//변경할 항목 배열에 등록.
	let modItems = [name, mail, hire, job];

	let newTr = document.createElement('tr');
	//사원번호 변경.
	let td = document.createElement('tr');
	td.innerText = id; //변경불가항목.
	newTr.append(td);
	//이름변경.
	modItems.forEach(item => {
		td = document.createElement('td');
		let inp = document.createElement('input');
		inp.style = "width: 100px";
		inp.value = item;   //.뒤에 속성추가
		td.append(inp);
		newTr.append(td);
	})
	// 삭제: 비활성화, 수정: 변경으로 DB반영.
	let btn = document.createElement('button');
	btn.innerText = '삭제';
	btn.disabled = true;
	td = document.createElement('td');
	td.append(btn);
	newTr.append(td);
	// 변경버튼.
	btn = document.createElement('button');
	btn.innerText = '변경';
	btn.addEventListener('click', updateMemberFnc); //updateMemberFnc() => 버튼을 클릭할 때가 아닌 코드를 읽을때 실행된다.
	td = document.createElement('td');
	td.append(btn);
	newTr.append(td);

	thisTr.replaceWith(newTr);    //기존의 tr를 새로우 tr로 바꿔준다.

	// 수정 처리 함수.
	function updateMemberFnc() {
		let currTr = this.parentElement.parentElement;
		let id = currTr.children[0].innerText;
		let name = currTr.children[1].children[0].value;
		let mail = currTr.children[2].children[0].value;
		let hDate = currTr.children[3].children[0].value;
		let job = currTr.children[4].children[0].value;
		console.log(id, name, mail, hDate, job);
		fetch('../empListJson', {
			method: "POST",
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'param=update&id=' + id + '&name=' + name + '&mail=' + mail + '&hire=' + hDate + '&job=' + job
		})
			.then(resolve => resolve.text())
			.then(result => {
				console.log(result)
				if (result.indexOf('Success') != -1) {
					alert('정상적으로 저리 되었습니다.')
					let newTr = makeTr({ id: id, lastName: name, email: mail, hireDate: hDate, jobId: job });
					currTr.replaceWith(newTr);
				} else {
					console.log('error 발생...')
				}
			})
			.catch(reject => console.log(reject))
	}
}

// 저장버튼 이벤트 콜백함수.
function addMemberFnc(evnt) {     //이벤트 콜백함수는 이벤트를 매개변수로 받는다.
	evnt.preventDefault(); // submit은 페이지를 이동하기 하기 때문에 이 메소드를 이용해 페이지 이동을 막는다.	
	let id = document.querySelector('input[name = "emp_id"]').value;
	let name = document.querySelector('input[name = "last_name"]').value;
	let mail = document.querySelector('input[name = "email"]').value;
	let hDate = document.querySelector('input[name = "hire_date"]').value;
	let job = document.querySelector('input[name = "job_id"]').value;

	if (!id || !name || !mail || !hDate || !job) {
		alert('필수 입력값을 확인하세요!');
		return;
	}
	fetch('../empListJson', {
		method: 'Post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },  //key=value key1=value1 처럼 쌍으로 넘겨준다.
		body: 'param=add&id=' + id + '&name=' + name + '&mail=' + mail + '&hire=' + hDate + '&job=' + job
	})
		.then(resolve => resolve.json())
		.then((result) => {
			if (result.retCode == 'Success') {
				alert('정상처리');
				list.append(makeTr({ id: id, lastName: name, email: mail, hireDate: hDate, jobId: job })); //item이 object 형식. parameter이름: 값
				//입력항목 초기화.
				document.querySelector('input[name = "emp_id"]').value = '';
				document.querySelector('input[name = "last_name"]').value = '';
				mail = document.querySelector('input[name = "email"]').value = '';
				document.querySelector('input[name = "hire_date"]').value = '';
				document.querySelector('input[name = "job_id"]').value = '';
			} else if (resutl.retCode == 'Fail') {
				alert('처리 중 오류 발생')
			}
		})
}

//전체선택 체크박스.
function allCheckChange() {
	console.log(this.checked);
	//tbody에 있는 체크박스 선택
	document.querySelectorAll('tbody input[type="checkbox"]').forEach(chk => {
		chk.checked = this.checked
	});
}

//선택삭제 처리.
// fetch API => 비동기방식처리. => 동기식 처리.(async, await) 
async function deletedCheckedFnc() {
	let ids = [];
	let chks = document.querySelectorAll('#list input[type="checkbox"]:checked');

	for (let i = 0; i < chks.length; i++) {
		let id = chks[i].parentElement.parentElement.firstChild.innerText;
		let resp = await fetch("../empListJson?del_id=" + id, {
			method: "DELETE",
		})
		let json = await resp.json();
		console.log(json);
		ids.push(json);
	}
	/*.then((resolve) => resolve.json())
	.then((result) => {
		if (result.retCode == "Success") {
			//chk.parentElement.parentElement.remove();
			ids.push(id);
		} else if (result.retCode == "Fail") {
			console.log('error: ' + id);
		}
	})
	.catch((reject) => console.log(reject));
}
)*/
	console.log('ids>>> ', ids);

	processAfterFetch(ids);   //자동새로고침  {id:101,retCode:Success},{id:102,retCode:Fail},...
}

//화면처리.
function processAfterFetch(ary = []) {
	let targetTr = document.querySelectorAll('#list tr');
	console.log(targetTr, 'vs', ary);
	//tr의 첫번째 자식(td)의 id(innerText)와 받아오는 오는값(ary)의 id와 비교해서 맞으면 삭제.
	targetTr.forEach(tr => {
		for (let i = 0; i < ary.length; i++) {
			if (tr.children[0].innerText == ary[i].id) {
				if (ary[i].retCode == "Success") {
					tr.remove(); //Success조건 하에 삭제					
				} else {
					tr.setAttribute('class', 'delError');
				}
			}
		}
	})
}
/*document.querySelectorAll('tbody input[type="checkbox"]:checked').forEach(chk => {
//삭제처리 같은 기능을 구현해보세요.
chk.addEventListener("click", selectDeleteRowFunc(chk));   // click이벤트가 실행되면 deleteRowFunc이 실행.*/

//선택삭제 이벤트
/*function selectDeleteRowFunc(chk) {
	let id = chk.parentElement.parentElement.firstChild.innerText;
	fetch("../empListJson?del_id=" + id, {
		method: "DELETE",
		)
		.then((resolve) => resolve.json())
		.then((result) => {
			if (result.retCode == "Success") {
				chk.parentElement.parentElement.remove();
				 else if (result.retCode == "Fail") {
				console.log('error: ' + id);
				
			)
		.catch((reject) => console.log(reject));
		*/
// 페이지 목록()
function showPages(curPage = 5) {
	document.querySelectorAll('#paging a').forEach((item) => item.remove());  //item = paging.a(페이징의 하위요소 a전부)	
	//전체건수
	
	let totalCnt = parseInt(localStorage.getItem('total')); // 내가가지고 있는 데이터만큼 보여준다
	let pageCnt = parseInt(localStorage.getItem('pageCnt'));
	let endPage = Math.ceil(curPage / 10) * 10;   //현재페이지가 15라면 15/10=1.5 의 올림(ceil)*10 = 20
	let startPage = endPage - 9;   //11
	let realEnd = Math.ceil(totalCnt / pageCnt); // 페이징 수
	let prev, next;  //이전 페이지 목록이 있는지, 다음 페이지 목록이 있는지

	endPage = endPage > realEnd ? realEnd : endPage;
	prev = startPage > 1 ? true : false;
	next = endPage < realEnd ? true : false;   //진짜 마지막 페이지로 왔을 때 >>를 붙이지 않기위해.
	let paging = document.getElementById("paging");
	// prev & next의 여부
	if (prev) {
		let aTag = document.createElement("a");
		aTag.addEventListener('click', showListPages);
		aTag.href = '#';
		aTag.page = startPage - 1;
		aTag.innerHTML = '&laquo;'//startPage - 1;
		paging.append(aTag);
	}
	for (let i = startPage; i <= endPage; i++) {
		let aTag = document.createElement("a");
		aTag.addEventListener('click', showListPages);
		aTag.href = '#';
		aTag.innerText = i;
		aTag.page = i; // innerText 속성이 페이지값을 활용.
		if(i == curPage){
			aTag.className = 'active' // aTag.setAttribute('class', 'active');
		}
		paging.append(aTag);
	}
	if (next) {
		let aTag = document.createElement("a");
		aTag.addEventListener('click', showListPages);
		aTag.href = '#';
		aTag.page = endPage + 1;
		aTag.innerHTML = '&raquo;'//endPage + 1;
		paging.append(aTag);
	}
}
// 페이지 클릭하면 해당되는 페이지 목록, 사원 목록을 보여주기 위한 함수.
function showListPages(e) {
	console.log(e.target.innerText);
	let page = e.target.page;
	showPages(page);
	employeeList(page);
}

//건수보기
let pageCnt = document.getElementById('pageCnt');
pageCnt.addEventListener('change', function() {
	localStorage.setItem('pageCnt',pageCnt.value);
	showPages(1);
	employeeList(1);
})

// 사원 목록()
function employeeList(curPage) {
	document.querySelectorAll('#list tr').forEach((item) => item.remove());
	let pageCnt = parseInt(localStorage.getItem('pageCnt'));
	let end = curPage * pageCnt; //몇개씩 보여주겠다. 
	let start = end - (pageCnt-1);
	let newList = totalAry.filter((emp, idx) => {
		return (idx + 1) >= start && idx < end;
	})
	let lst = document.getElementById('list');
	newList.forEach(emp => {
		let tr = makeTr(emp);
		lst.append(tr);
	})
}


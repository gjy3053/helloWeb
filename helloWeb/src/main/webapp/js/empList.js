
/**
 * empList.js
 */
//목록출력하기.
fetch("../empListJson")  //아작스 호출.
	.then((resolve) => resolve.json())   // 가져온 데이터를 제이슨으로 바꿔준다
	.then((result) => {
		//배열관련 메소드: forEach, map, filter, reduce 메소드.
		result.forEach(function(item, idx, arry) {
			let tr = makeTr(item); //tr생성 후 반환.
			list.append(tr);
		});
	})
	.catch((reject) => {
		console.log(reject);
	});
//저장버튼 submit 이벤트 등록.
document.querySelector('form[name="empForm"]').addEventListener('submit', addMemberFnc);  // 폼 태그중에서 이름이 empForm인것을 가져오겠습니다.

//전체선택 체크박스
document.querySelector('thead input[type = "checkbox"]').addEventListener('change', allCheckChange);

//선택삭제 버튼
document.querySelector('#delSelectedBtn').addEventListener('click', deleteCheckedFnc);


// 데이터 한건을 활용해서 tr이라는 요쇼를 생성.
function makeTr(item) {  //매개값으로 받아오면 그 값을 가지고 tr을 만들어서 반환해준다 , item은 객체타입{}
	//result베열에 들어있는 값의 갯수만큼
	//DOM요소생성.
	let titles = ["id", "lastName", "email", "hireDate", "job"]; //json이랑 이름 같아야함
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
	td.append(chk);
	tr.append(td);
	//tr.반환
	return tr;
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
				alert("삭제중 오류 발생");
			}
		})
		.catch((reject) => console.log(reject));
}
//선택삭제 콜백함수
function deleteCheckFunc(chk) {
	let id = chk.parentElement.parentElement.firstChild.innerText;
	fetch("../empListJson?del_id=" + id, {
		method: "DELETE",
	})
		.then((resolve) => resolve.json())
		.then((result) => {
			console.log(result);
			if (result.retCode == "Success") {
				alert("정상적으로 삭제 되었습니다.");
				chk.parentElement.parentElement.remove();
			} else if (result.retCode == "Fail") {
				alert("삭제중 오류 발생");
			}
		})
		.catch((reject) => console.log(reject));
}

//수정 화면 함수
function modifyTrFunc() {
	//this => 수정버튼
	let thisTr = this.parentElement.parentElement; //수정 버튼 -> td-> tr
	console.log("사원번호 : ", thisTr.children[0].innerText);
	console.log("이름 : ", thisTr.children[1].innerText);

	let id = thisTr.children[0].innerText;
	let name = thisTr.children[1].innerText;
	let mail = thisTr.children[2].innerText;
	let hire = thisTr.children[3].innerText;
	let job = thisTr.children[4].innerText;
	//변경할 항목 배열에 등록
	let modItems = [name, mail, hire, job];

	let newTr = document.createElement('tr');
	//사원번호 변경
	let td = document.createElement('td');
	td.innerText = id; //변경불가항목
	newTr.append(td);
	//이름변경
	modItems.forEach(item => {
		td = document.createElement('td');
		let inp = document.createElement('input');
		inp.style = 'width: 100px';
		inp.value = item;
		td.append(inp);
		newTr.append(td);
	})
	//삭제: 비활성화, 변경: DB반영.
	let btn = document.createElement('button');
	btn.innerText = '삭제';
	btn.disabled = true; //비활성화
	td = document.createElement('td');
	td.append(btn);
	newTr.append(td);
	//변경버튼
	btn = document.createElement('button');
	btn.innerText = '변경';
	btn.addEventListener('click', updateMemberFnc);
	td = document.createElement('td');
	td.append(btn);
	newTr.append(td);
	

	
	
	thisTr.replaceWith(newTr);

}

//수정처리함수
function updateMemberFnc() {
	let currTr = this.parentElement.parentElement;
	let id = currTr.children[0].innerText;
	let name = currTr.children[1].children[0].value; //input태그는 value
	let mail = currTr.children[2].children[0].value;
	let hDate = currTr.children[3].children[0].value;
	let job = currTr.children[4].children[0].value;
	//console.log(id, name, mail, hDate, job);
	
	fetch('../empListJson', {
		method : 'POST',
		headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
		body : 'param=update&id=' + id + '&name=' + name + '&mail=' + mail + '&hire=' + hDate + '&job=' + job
	})
	.then(resolve => resolve.text())
	.then(result => {
		console.log(result)
		if(result.indexOf('Success') != -1) {
			alert("정상적으로 처리");
			let newTr = makeTr({ id: id, lastName: name, email: mail, hireDate: hDate, job: job });
			currTr.replaceWith(newTr);
		}else {
			console.log('error발생..')
		}
	})
	.catch(reject => console.log(reject))
	
}

// 저장버튼 이벤트 콜백함수.
function addMemberFnc(evnt) {     //이벤트 콜백함수는 이벤트를 매개변수로 받는다.
	evnt.preventDefault(); // submit은 페이지를 이동하기 하기 때문에 이 메소드를 이용해 페이지 이동을 막는다.
	console.log('여기에 출력.');
	let id = document.querySelector('input[name="emp_id"]').value;
	let name = document.querySelector('input[name="last_name"]').value;
	let mail = document.querySelector('input[name="email"]').value;
	let hDate = document.querySelector('input[name="hire_date"]').value;
	let job = document.querySelector('input[name="job_id"]').value;

	if (!id || !name || !mail || !hDate || !job) {
		alert("필수입력값을 확인하세요!!");
		return;
	}

	fetch("../empListJson", {
		method: 'POST',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, // key-value형식
		body: 'param=add&id=' + id + '&name=' + name + '&mail=' + mail + '&hire=' + hDate + '&job=' + job
	})
		.then(resolve => resolve.json()) //function(resolve) {return resolve.json()}
		.then(result => {
			if (result.retCode == 'Success') {
				alert("정상적으로 처리되었습니다.");
				list.append(makeTr({ id: id, lastName: name, email: mail, hireDate: hDate, job: job }));
				//입력항목 초기화
				document.querySelector('input[name="emp_id"]').value = '';
				document.querySelector('input[name="last_name"]').value = '';
				document.querySelector('input[name="email"]').value = '';
				document.querySelector('input[name="hire_date"]').value = '';
				document.querySelector('input[name="job_id"]').value = '';

			} else if (result.retCode == 'Fail') {
				alert("처리중 에러 발생");
			}
		})
}

//전체선택 체크박스
function allCheckChange(){
	console.log(this.checked);
	//tbody에 있는 체크박스 선택.
	document.querySelectorAll('tbody input[type = "checkbox"]').forEach(chk => {
		chk.checked = this.checked;
	})
	
}

// 선택삭제 처리
function deleteCheckedFnc() {
	document.querySelectorAll('tbody input[type = "checkbox"]:checked').forEach(chk => {
		//console.log(chk);
		chk.addEventListener("click", deleteCheckFunc(chk));
		
	})
}







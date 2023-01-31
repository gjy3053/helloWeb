
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

// 데이터 한건을 활용해서 tr이라는 요쇼를 생성.
function makeTr(item) {  //매개값으로 받아오면 그 값을 가지고 tr을 만들어서 반환해준다 , item은 객체타입{}
	//result베열에 들어있는 값의 갯수만큼
	//DOM요소생성.
	let titles = ["id", "firstName", "email", "hireDate", "job"]; //json이랑 이름 같아야함
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
// 저장버튼 이벤트 콜백함수.
function addMemberFnc(evnt){     //이벤트 콜백함수는 이벤트를 매개변수로 받는다.
	evnt.preventDefault(); // submit은 페이지를 이동하기 하기 때문에 이 메소드를 이용해 페이지 이동을 막는다.
	console.log('여기에 출력.');
	let id = document.querySelector('input[name="emp_id"]').value;
	let name = document.querySelector('input[name="last_name"]').value;
	let mail = document.querySelector('input[name="email"]').value;
	let hDate = document.querySelector('input[name="hire_date"]').value;
	let job = document.querySelector('input[name="job_id"]').value;
	
	if(!id || !name || !mail || !hDate || !job ) {
		alert("필수입력값을 확인하세요!!");
		return;
	}
	
	fetch("../empListJson", {
		method:'POST',
		headers : {'Content-Type' : 'application/x-www-form-urlencoded'}, // key-value형식
		body: 'id=' + id + '&name=' + name + '&mail=' + mail + '&hire=' + hDate + '&job=' + job
	})
	.then(resolve => resolve.json()) //function(resolve) {return resolve.json()}
	.then(result => {
		if(result.retCode == 'Success'){
			alert("정상적으로 처리되었습니다.");
			list.append(makeTr({id: id, firstName: name, email: mail, hireDate: hDate, job: job}));
		}else if(result.retCode == 'Fail'){
			alert("처리중 에러 발생");
		}
	})
}










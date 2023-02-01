
/**
 * projectSignUp.js
 */	
fetch("../projectJson")  //아작스 호출.
	.then((resolve) => resolve.json())   // 가져온 데이터를 제이슨으로 바꿔준다
	.catch((reject) => {
		console.log(reject);
	});

	
 document.querySelector('button[name="sign"]').addEventListener('submit', addCustomerFnc);
 // 회원가입버튼 이벤트 콜백함수.
function addCustomerFnc(evnt) {     //이벤트 콜백함수는 이벤트를 매개변수로 받는다.
	evnt.preventDefault(); // submit은 페이지를 이동하기 하기 때문에 이 메소드를 이용해 페이지 이동을 막는다.
	console.log("!");
/*	let id = document.querySelector('input[name="id"]').value;
	let pw = document.querySelector('input[name="pw"]').value;
	let addr = document.querySelector('input[name="addr"]').value;

	if (!id || !pw || !addr) {
		alert("필수입력값을 확인하세요!!");
		return;
	}

	fetch("../projectSignUp", {
		method: 'POST',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, // key-value형식
		body: 'id=' + id + '&pw=' + pw + '&addr=' + addr 
	})
		.then(resolve => resolve.json()) //function(resolve) {return resolve.json()}
		.then(result => {
			if (result.retCode == 'Success') {
				alert("정상적으로 처리되었습니다.");
				//list.append(makeTr({ id: id, lastName: name, email: mail, hireDate: hDate, job: job }));
				//입력항목 초기화
				document.querySelector('input[name="id"]').value = '';
				document.querySelector('input[name="pw"]').value = '';
				document.querySelector('input[name="addr"]').value = '';
			

			} else if (result.retCode == 'Fail') {
				alert("처리중 에러 발생");
			}
		})*/
		
}
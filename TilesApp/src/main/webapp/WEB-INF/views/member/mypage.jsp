<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<h3>현재페이지는 myPageForm.do의 결과 mypage.jsp입니다</h3>
<form action="modifyMember.do" method="post">
<input type="file" id="fileUpload" accept="image/*" style="display: none" onchange="imageChageFnc()">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="mid" value="${vo.memberId}"
				readonly></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="mname" value="${vo.memberName}"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="mpass" value="${vo.memberPw}"></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="mphone" value="${vo.memberPhone}"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="maddr" value="${vo.memberAddr}"></td>
		</tr>
		<tr>
			<th>image</th>
			<td><img id="imgSrc" width="150px" src="upload/${vo.image }"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="수정"></td>
		</tr>
	</table>
</form>

<script>
//제이쿼리 이벤트 등록 -> elem.on('click', function() {})
 $('#imgSrc').on('click', function() {
	$('#fileUpload').click();	
})
 
 
 function imageChageFnc() {
	console.log($('#fileUpload')[0].files[0]);
	let file = $('#fileUpload')[0].files[0];
	
	let formData = new FormData(); //multipart처리
	formData.append('id',"${vo.memberId}" ); //id, file업로드 : db변경.
	formData.append('image',file);
	
	//서버에 multipart/form-data :ajax요청
	$.ajax({
		url:'imageUpload.do',
		method:'post',
		data: formData,
		contentType: false, //multipart요청일 경우에 옵션
		processData: false, //multipart요청일 경우에 옵션
		success: function(result) {
			console.log(result);
			//화면에서도 선택된 이미지가 보여줌
			let reader = new FileReader();
			reader.onload = function(evnt) {
				console.log(evnt.target);
				$('#imgSrc').attr('src', evnt.target.result);
				
			}
			reader.readAsDataURL(file);
			
		},
		error: function(err) {
			console.log(err);
		}
	})
	
}
</script>
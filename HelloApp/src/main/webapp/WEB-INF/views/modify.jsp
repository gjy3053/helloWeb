<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<% EmpVO emp = (EmpVO) request.getAttribute("searchVO"); 
%>
<form name = "myFrm" >
		<label>사원번호 : </label>
		
		<input type = "number" name = "eid" ><br>  
		<label>LastName : </label>
		<input type = "text" name = "last_name"><br>
		<label>이메일 : </label>
		<input type = "email" name = "email"><br>
		<label>입사일자 : </label>
		<input type = "date" name = "hire_date"><br>
		<label>직무 : </label>
		<select name ="job">
			<option value = "IT_PROG">개발자</option>
			<option value = "SA_REP" selected>영업사원</option>
			<option value = "SA_MAN">영업팀장</option>
		</select>
		<input type = "submit" value = "전송">
	</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>
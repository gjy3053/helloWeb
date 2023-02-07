<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<form name="myFrm" action="./empModify.do" method="get">
	<!-- employee.do에서 받아준다. EmpControl로 요청 -->
	<%
	EmpVO emp = (EmpVO) request.getAttribute("searchVO");
	Map<String, String> list = (Map<String, String>) request.getAttribute("jobList");
	%>
	<h3>현재 페이지는 emp.ModForm.do의 결과 modify.jsp입니다.</h3>
	<table class="table">
	<tr>
	<th>사원번호:</th>
	<td> <input type="text" name="eid" value="<%=emp.getEmployeeId()%>" readonly></td> 
	</tr>
	<tr>
	<th>FirstName:</th>
	<td><input type="text" name="fname" value="<%=emp.getFirstName()%>"></td>
	</tr>
	<tr>
	<th>LastName:</th>
	<td><input type="text" name="lname" value="<%=emp.getLastName()%>"></td>
	</tr>
	<tr>
	<th>이메일:</th>
	<td><input type="email" name="email" value="<%=emp.getEmail()%>"></td>
	</tr>
	<tr>
	<th>입사일자:</th>
	<td><input type="date" name="hire" value="<%=emp.getHireDate()%>"></td>
	</tr>
	<tr>
	<th>직무:</th>
	<td> <input type="text" name="job" value="<%=emp.getJobId()%>" id = job_name>
	<select name="job" onchange = func(this.value)> 
	<%
			for (Entry<String, String> ent : list.entrySet()) {
			%>
			<option value="<%=ent.getKey()%>"><%=ent.getValue()%></option>
			<%
			}
			%>
	</select>
	</td>
	</tr>
	<tr>
	<td colspan = '2' align = "center">
	<input type="submit" value="수정" >
	</td>
	</tr>
</table>
</form>
<script>
function func(job) {
	document.myFrm.job_name.value =job;
}
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>
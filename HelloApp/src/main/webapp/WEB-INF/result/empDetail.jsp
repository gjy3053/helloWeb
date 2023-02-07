<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	EmpVO emp = (EmpVO) request.getAttribute("searchVO");
%>
<%
	String id = (String) session.getAttribute("id");
%>
<h3>현재 페이지는 empDetail.do의 결과 empDetail.jsp입니다.</h3>
<table class="table">
	<tr>
		<th>사원번호</th>
		<td><%=emp.getEmployeeId()%></td>
	</tr>
	<tr>
		<th>FirstName</th>
		<td><%=emp.getFirstName()%></td>
	</tr>
	<tr>
		<th>LastName</th>
		<td><%=emp.getLastName()%></td>
	</tr>
	<tr>
		<th>메일</th>
		<td><%=emp.getEmail()%></td>
	</tr>
	<tr>
		<th>직무</th>
		<td><%=emp.getJobId()%></td>
	</tr>
	<%
	if (id != null) {
	%>
	<tr>
		<td colspan="2" align="center">
			<button class="btn btn-primary"
				onclick="location.href='empModForm.do?id=<%=emp.getEmployeeId()%>'">수정</button>
			<button class="btn btn-warning"
				onclick="location.href='empRemove.do?eid=<%=emp.getEmployeeId()%>'">삭제</button>
			<!--empRemove.do?id=?? removeEmp(int id) 삭제할때는 vo타입필요없음 -->
	</tr>
	<%
	}
	%>
</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>
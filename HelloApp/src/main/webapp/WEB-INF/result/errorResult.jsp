<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result/errorResult.jsp</title>
</head>
<body>
	<h3>처리중 에러가 발생!</h3>
	
	<c:set var = "myname" value="홍길동"></c:set> <%-- <%String name = "홍길동" %> --%>
	<c:out value = "${myname}"></c:out>
	<p>${myname }</p>
	
	<c:set var="score" value="75"></c:set>
	<c:if test="${score>60 }">
		<p>합격!</p>
	</c:if>
	<c:if test="${score<60 }">
		<p>불합격!</p>
	</c:if>
	
	<p><if-else문></p>
	<c:choose>
		<c:when test="${score>90 }"><p>A학점</p></c:when>
		<c:when test="${score>80 }"><p>B학점</p></c:when>
		<c:when test="${score>70 }"><p>C학점</p></c:when>
		<c:otherwise><p>D학점</p></c:otherwise>
	</c:choose>
	
	<p><1부터 10까지 출력></p>
	<c:forEach begin="1" end="10" var="num">
		<p class='<c:out value="${num }"/>'>${num }</p>
	</c:forEach>
	
	<p><구구단 3단></p>
	<c:set var="dan" value="3"></c:set> <!-- 값지정하기-> set -->
	<c:forEach begin="1" end="9" var="num">
		<p class='<c:out value="${num*3}"/>'> ${dan} * ${num} = ${num*dan }</p>
	</c:forEach>
	
	<p>empList출력</p>
	<c:forEach items="${empList }" var="emp">
		<p>사번 : ${emp.employeeId }, 이름 : ${emp.firstName }, 이메일 : ${emp.email }, 직무: ${emp.jobId } </p>
	=</c:forEach>
	
	
	
	
	<h3>Expression Language (EL)</h3>
	<p>${3>5}</p>
	<p>${'test'=='test'}</p>
	<p>${5-4 }</p>
	<p>${5*4 }</p>
	<p>${id } EL안에서 ${id } => attribute()</p>
	<p>${id eq null}</p>
	<p>${empty id}</p> <!-- id가 null이냐 공백이냐 -->
	<p>${obj}</p>
	<p>ID : ${obj.memberId }</p>
	<p>name : ${obj.memberName }</p>
	<p>List : ${empList }</p>
	
	<p>html안에 자바코드 제거 => jstl연습</p>
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- noticeListAjax.jsp => return "notice/noticeListAjax.tiles"
서버 : noticeListJson.do -> json데이터 반환 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link
	href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
	<br>
<div>
 작성자 : <input type="text" id="writer" value="user1" readonly>
 제목 : <input type="text" id="title">
 내용 : <input type="text" id="subject">
 <button id="addBtn">저장</button>
 <button id="delBtn">삭제</button>
</div>
<br>
<table id="example" class="display" style="width: 100%">
	<thead>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일자</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일자</th>
		</tr>
	</tfoot>
</table>



<script>
	$(document).ready(function() {
		$('#example').DataTable({
			ajax : 'noticeListJson.do',
		});
	});
	
	$(document).ready(function () {
	    var table = $('#example').DataTable();
	 
	    $('#example tbody').on('click', 'tr', function () {
	        if ($(this).hasClass('selected')) {
	            $(this).removeClass('selected');
	           
	        } else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    });
	 
	    $('#delBtn').click(function () {
	    	console.log();
	        table.row('.selected').remove().draw(false);
	       // console.log(table.row('.selected').child);
	    	$.ajax({
				url:'noticeListAjaxDel.do',
				method: 'post',
				//data: {id:  },
				success: function(result) {
					console.log(result);
					if(result.retCode=="Success") {
					 	alert("삭제");
					 	
					}else {
						alert("처리안됨");
					}
				},
				error: function(reject){
					console.log(reject);
				}
			});
	    });
	});
	
	
</script>
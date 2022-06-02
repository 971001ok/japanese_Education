<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp"%>
	
<c:if test="${empty userid}">
	<script>
		location.href="jlogin.do";
	</script>
</c:if>	
	
<div class="gnb" style="padding-top:20px; text-align:center;" onsubmit="return check()">
   	<div class="group">
		<h2>Q&A投稿</h2>
		<div>
			<form name="qna" method="post" action="qnawritepro.do" >
				<table style="margin-left:auto; margin-right:auto;">
					<colgroup>
						<col width="150px">
						<col width="600px">
					</colgroup>
   						
					<tbody>
						<tr>
							<th scope="col">ID</th>
							<td><input type="text" name="writer"></td>
						</tr>
						<tr>
							<th scope="col">タイトル</th>
   							<td><input type="text" name="title"></td>
   						</tr>
   						<tr>
 							<th scope="col">内容</th>
   							<td>
								<textarea name="qcontent" id="qcontent" style="width:100%;height:300px; border:1px solid #ccc;"></textarea>
   							</td>
	   					</tr>
					</tbody>
   				</table>
				<div class="pagination">
					 <input type="submit" class="btn_ok" value="投稿">
					 <input type="reset" class="btn_reset" value="書き直し">
				</div>
			</form>
   		</div>
  	</div>
</div>
<script>
$(function() {
	$("#btn_ok").on("click", function() {
		if(!$("#writer").val()){
			alert("IDをご入力ください");
			$("#writer").focus();
			return false;
		}
		if(!$("#title").val()){
			alert("タイトルをご入力ください");
			$("#title").focus();
			return false;
		}
		if(!$("#qcontent").val()){
			alert("内容をご入力ください");
			$("#qcontent").focus();
			return false;
		}
	$("#qna").submit();
	
	})
});// end
</script>
<script>
	function check() {
		if(qna.writer.value=="") {
			alert("IDをご入力ください");
			qna.writer.focus();
			return false;
		}
		if(qna.title.value=="") {
			alert("タイトルをご入力ください");
			qna.title.focus();
			return false;
		}
		if(qna.qcontent.value=="") {
			alert("内容をご入力ください");
			qna.qcontent.focus();
			return false;
		}
		return true;
	}

</script>
<%@ include file="../footer.jsp"%>
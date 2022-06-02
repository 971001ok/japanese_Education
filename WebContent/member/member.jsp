<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp"%>

<div class="id">
	<h2>会員登録</h2>
	<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
		<form name="member" id="member" method="post" action="member.do">
			<table>
				<colgroup>
					<col width="12%">
					<col width="*">
				</colgroup>
				<tbody class="table_pw">
					<tr>
						<th scope="col" >ID</th>
						<td style="text-align:left;">
						<input type="text" name="id" id="id" style="width:200px;"><p id="idmsg"></p>
						</td>         
					</tr>
					<tr>
						<th scope="col" >パスワード</th>
						<td><input type="password" name="pw1" id="pw1"></td>         
					</tr>
					<tr>
						<th scope="col" >パスワード確認</th>
						<td><input type="password" name="pw2" id="pw2"></td>         
					</tr>
					<tr>
						<th scope="col" >E-mail</th>
						<td>
						<input type="text" name="email" id="email">
						</td>         
					</tr>
					<tr>
						<th scope="col" >連絡先</th>
						<td>
						<input type="text" name="phone" id="phone" >
						</td>         
					</tr>
					<tr>
						<th scope="col" >郵便番号</th>
						<td style="text-align:left;">
						<input type="text" name="post" id="post" style="width:120px;">
						</td>         
					</tr>
					<tr>
						<th scope="col" >住所</th>
						<td>
						<input type="text" name="address1" id="address1"><br>
						<input type="text" name="address2" placeholder="残りの住所をお書きください">
						</td>         
					</tr>
				</tbody>
			</table>
		         
		<div class="pagination" style="text-align:center; padding-top:30px;">
			<a href="javascript:void(0);" id="btn_ok"><b style="border: 1px solid; padding: 10px;">会員登録</b></a>
		</div>
		</form>
	</div>
</div>
	
<script>
$(function() {

	$("#btn_ok").on("click", function() {
		
		if(!$("#pw1").val()){
			alert("パスワードをご入力ください");
			$("#pw1").focus();
			return false;
		}
		if(!$("#pw2").val()){
			alert("パスワードご確認 ください");
			$("#pw2").focus();
			return false;
		}
		if(!$("#email").val()){
			alert("E-mailをご入力ください");
			$("#email").focus();
			return false;
		}
		if(!$("#phone").val()){
			alert("連絡先をご入力ください");
			$("#phone").focus();
			return false;
		}
		if(!$("#post").val()){
			alert("郵便番号をご入力ください");
			$("#post").focus();
			return false;
		}
		if(!$("#address1").val()){
			alert("住所をご入力ください");
			$("#address1").focus();
			return false;
		}
		
		$("#member").submit();
		})
			
		$("#id").blur(function(){
		if(!$("#id").val()){
			$("#idmsg").html("<span style='color:#f00;'>IDをご入力ください</span>")
		}
	
	
		$.ajax({
		
		type:'post',
		url:'memberIdCheck.do',
		data:{id:$("#id").val()},
		success:function(data) {
		//정상 요청, 응답 시 처리 작업
			if(data != 1){
			if($("#id").val() != ""){
		$("#idmsg").html("使用できます");
		}
		}else{
			if($("#id").val() != ""){
				$("#idmsg").html("使用中か脱退したIDです");
				$("#id").val("");
				$("#id").focus();
			}
		}
		},error : function(xhr,status,error) {
			alert("通信エラー");
			//오류 발생 시 처리
		}
		});
	})
});// end
</script>
			
<%@ include file="../footer.jsp"%>
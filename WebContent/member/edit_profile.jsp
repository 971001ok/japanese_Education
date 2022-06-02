<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp"%>

		
<div class="id">
	<h2>情報変更</h2>
	<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
		<form name="member" id="member" method="post" action="edit_profile.do">
			<table>
				<colgroup>
					<col width="12%">
					<col width="*">
				</colgroup>
				<tbody id="joinDataBady" class="table_pw">
					<tr>
						<th scope="col" >ID</th>
						<td style="text-align:left;">
						<input type="text" name="id" id="id" style="width:200px;" value="${edit.id }" readonly>
						<span id="idmsg"></span>
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
						<input type="text" name="email" id="email"  value="${edit.email }">
						</td>         
					</tr>
					<tr>
						<th scope="col" >連絡先</th>
						<td>
						<input type="text" name="phone" id="phone" value="${edit.phone }">
						</td>         
					</tr>
					<tr>
						<th scope="col" >郵便番号</th>
						<td style="text-align:left;">
						<input type="text" name="post" id="post" style="width:120px;" value="${edit.post }">
						</td>         
					</tr>
					<tr>
						<th scope="col" >住所</th>
						<td>
						<input type="text" name="address1" id="address1" value="${edit.address1 }"><br>
						<input type="text" name="address2" placeholder="残りの住所をお書きください" value="${edit.address2 }">
						</td>
					</tr>
				</tbody>
			</table>
		
			<div class="pagination" style="text-align:center;">
				<a href="javascript:void(0);" class="btn submit" id="btn_ok"><b>これに直す</b></a>
				<a href="javascript:history.go(-1);" class="btn reset"><b>元に戻る</b></a>
			</div>
		</form>
	</div>
</div>
      
<script>
$(function() {

	$("#btn_ok").on("click", function() {
		if(!$("#pw1").val()){
			alert("パスワードを入力してください");
			$("#pw1").focus();
			return false;
		}
		if(!$("#pw2").val()){
			alert("パスワードを確認してください");
			$("#pw2").focus();
			return false;
		}
		if(!$("#email").val()){
			alert("emailを入力してください");
			$("#email").focus();
			return false;
		}
		if(!$("#phone").val()){
			alert("連絡先を入力してください");
			$("#phone").focus();
			return false;
		}
		if(!$("#post").val()){
			alert("郵便番号を入力してください");
			$("#post").focus();
			return false;
		}
		if(!$("#address1").val()){
			alert("住所を入力してください");
			$("#address1").focus();
			return false;
		}
		
	$("#member").submit();
	
	})
});// end
</script>

<script>
	 function fn_save(){
		 // 입력받은 값들을 유효성 검사하고 
		 if(member.id.value){
			 alert("IDを入力してください");
			 member.id.focus();
			 return false;
		 }
		 if(member.email.value){
			 alert("emailを入力してください");
			 member.email.focus();
			 return false;
		 }
		 if(member.phone.value){
			 alert("連絡先を入力してください");
			 member.phone.focus();
			 return false;
		 }
		 if(member.post.value){
			 alert("郵便番号を入力してください");
			 member.post.focus();
			 return false;
		 }
		 if(member.address1.value){
			 alert("住所を入力してください");
			 member.address1.focus();
			 return false;
		 }
		 // 서버로 전송한다(action)
		 member.submit();
	 }
	</script>
	        
<%@ include file="../footer.jsp"%>
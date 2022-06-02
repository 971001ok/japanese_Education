<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

<div class="id">
	<h2>email確認</h2>
	<div class="formtable"> <!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
		<form name="login" id="login" >
			<table>
				<colgroup>
				<col width="20%">
				<col width="*">
				<col width="25%">
				</colgroup>
				<tr>
					<th scope="col" >email&nbsp;&nbsp;</th>
					<td><input type="text" name="email" id="email" placeholder="email入力"></td>
					<td rowspan="2">   
						<a class="botan" id="btn_email">email確認</a>
					</td>  
				</tr>
				<tr>
					<th scope="col" >認証番号&nbsp;&nbsp;&nbsp;</th>
					<td><input type="text" name="certinumber" id="certinumber" placeholder="認証番号入力"></td>
				</tr>
				    
				<tr>
				   <td colspan="3" style="padding: 10px 30px 0px 0px;">
				      <a class="botan" id="btn_ok">認証確認</a>
				   </td>         
				</tr>
			</table>
		</form>
	</div>
</div>
<!-- end contents -->

<script>
$(function() {
	
	//email 인증
	
	$("#btn_email").on("click", function(){
		var email = $("#email").val();
		if(email == ""){
			alert("emailアドレスを入力してください");
			$("#email").focus();
		}else{
			$.ajax({
				type:"post",
				url:"email.do",
				data:{"email":$("#email").val()}, 
				//위 세줄 먼저 실행 후 서블릿 실행 그다음 아래 줄 실행
				dataType:"json", //서버에서 보내주는 데이터 형식 //"emailsend.do",에서 리턴받는 형식을 json으로 받겠습니다
				success:function(data){
					alert(data.msg);
				},error:function(){
					alert("通信エラー");
				}
			})
		}
	})
	
	//인증확인 버튼 이벤트 처리
	$("#btn_ok").on("click", function(){
		var certinumber = $("#certinumber").val(); //var certinumber에 저장하시오
		//입력받은 인증번호
		if(certinumber == ""){
			alert("認証番号を入力してください");
			$("#certinumber").focus();
		}else{
			$.ajax({
				type:"post",
				data:{"certinumber":certinumber},
				url:"mypage.do",
				dataType:"json", 
				success:function(data){
					alert(data.msg);
					if(data.check == "ok"){
						$(location).attr("href","edit_profile.do"); //jquery형식
					}else{
						alert(data.msg);
					}
				},error:function(){
					alert("通信エラー");
				}
			})
		}
	})
}); //
	
</script>

<script>
function fn_login(){
	if(login.id.value == ""){
		alert("IDを入力してください");
		login.id.focus();
		return false;
	}
	if(login.pw.value == ""){
		alert("暗証番号を入力してください");
		login.pw.focus();
		return false;
	}
	var form = document.login;
	form.method='post';
	form.action="login.do";
	form.submit();
}
</script>
	
<%@ include file="../footer.jsp"%>
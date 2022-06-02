<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp"%>

<div class="id">
	<h2>ログイン</h2>
	<div class="formtable"> <!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
		<form name="login" id="login" >
			<table>
				<colgroup>
				<col width="20%">
				<col width="*">
				<col width="25%">
				</colgroup>
				<tr>
					<th scope="col" >ID&nbsp;&nbsp;</th>
					<td><input type="text" name="id" id="id"></td>
					<td rowspan="2">   
						<a class="botan" href="javascript:fn_login();">ログイン</a>  
					</td>  
				</tr>
				<tr>
					<th scope="col" >パスワード&nbsp;&nbsp;&nbsp;</th>
					<td><input type="password" name="pw" id="pw"></td>
				</tr>
				<tr>
					<td colspan="3" style="padding: 10px 30px 0px 0px;">
						<a class="botan" href="member.do">会員登録</a>
					</td>         
				</tr>
			</table>
		</form>
	</div>
</div>

<script>
$(function() {
	$(".location  .dropdown > a").on("click",function(e) {
		e.preventDefault();
		if($(this).next().is(":visible")) {
			$(".location  .dropdown > a").next().hide();
		} else {
			$(".location  .dropdown > a").next().hide();
			$(this).next().show();
		}
	});
});
</script>
<script>
	function fn_login(){
		if(login.id.value == ""){
			alert("IDを入力してください");
			login.id.focus();
			return false;
		}
		if(login.pw.value == ""){
			alert("パスワードを入力してください");
			login.pw.focus();
			return false;
		}
		var form = document.login;
		form.method='post';
		form.action="jlogin.do";
		form.submit();
	}
</script>
         
<%@ include file="../footer.jsp"%>
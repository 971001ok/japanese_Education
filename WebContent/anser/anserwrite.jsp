<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

<div class="gnb" style="padding-top:20px; text-align:center;">
  	<div class="group">
		<h2>答え</h2>
		<div>
		<form name="photo" method="post" enctype="multipart/form-data" action="anserwrite.do"　onsubmit="return check()">
			<table style="margin-left:auto; margin-right:auto;">
				<colgroup>
				<col width="100px">
				<col width="600px">
				</colgroup>
				
				<tbody>
					<tr>
						<th>タイトル</th>
						<td><input type="text" id="title"　name="title"></td>
					</tr>
					<tr>
						<th scope="col">内容</th>
						<td><input type="text" name="content" id="content" class="naiyou" style="height:300px;"></td>
					</tr>
					<tr>
						<th scope="col">添付ファイル</th>
					<th class="filebox">
						<label for="ex_file">アップロード</label>
						<input type="file" name="imgFile"id="ex_file">
					</th>
					</tr>
				</tbody>
			</table>
			<div class="pagination">
				<input type="submit" class="btn info" value="投稿">&nbsp;&nbsp;
				<input type="reset" class="btn info" value="書き直す">
			</div>
		</form>
		</div>
	</div>
</div>
	
<script>
function check() {
	
	if(photo.title.value=="") {
		alert("タイトルをご入力ください");
		photo.title.focus();
		return false;
	}
	if(photo.content.value=="") {
		alert("内容をご入力ください");
		photo.content.focus();
		return false;
	}
	var imgFile = $('#ex_file').val();
	      var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
	      var maxSize = 20 * 1024 * 1024;
	      var fileSize;
	
	      if($('#ex_File').val() == "") {
			alert("ファイルを添付してください");
	         	$("#ex_file").focus();
			return false;
	}
	
	if(imgFile != "" && imgFile != null) {
		fileSize = document.getElementById("ex_file").files[0].size;
	            
	if(!imgFile.match(fileForm)) {
		alert("イメージファイルだけ添付できます");
		return false;
	} else if(fileSize >= maxSize) {
		alert("20MBまで入れられます");
		return false;
	}
	}
		return true;
	}
</script>
<script>
$(function() {
	$("#btn_ok").on("click", function() {
		if(!$("#title").val()){
			alert("タイトルをご入力ください");
			$("#title").focus();
			return false;
		}
		if(!$("#content").val()){
			alert("内容をご入力ください");
			$("#content").focus();
			return false;
		}
		if(!$("#ex_file").val()){
			alert("ファイルを添付してください");
			$("#ex_file").focus();
			return false;
		}
	$("#photo").submit();
	
	})
});// end
</script>
		
<%@ include file="../footer.jsp"%>
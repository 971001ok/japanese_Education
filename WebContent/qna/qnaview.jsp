<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp"%>

<div class="gnb" style="padding-top:20px; text-align:center;">

	<h1>Q&A</h1>
	
	<h2>${view.title}</h2>
	<p class="info"><span class="user">${view.writer}</span> | <i class="fa fa-eye"></i> ${view.viewcount} </p>
	<div class="board_body">
		${view.qcontent}
	</div>
	
	<div class="prev_next">
		<c:if test="${prev.qbno != 0}">
			<a href="qnaview.do?qbno=${prev.qbno}&pageNum=${cri.pageNum}&amount=${cri.amount}" class="btn_prev">
				<i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
				<strong>以前の書き込み</strong><span>${prev.title}</span>
				</span>
			</a>
		</c:if>
		<c:if test="${prev.qbno == 0}">
			<a class="btn_prev">
				<i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
				<strong>最後の</strong><span>書き込みです</span>
				</span>
			</a>
		</c:if>
		<div class="btn_3wrap">
			<a href="qna.do?pageNum=${cri.pageNum}&amount=${cri.amount}">リスト</a> 
			<a href="qnaEdit.do?qbno=${view.qbno}&pageNum=${cri.pageNum}&amount=${cri.amount}">直す</a> 
			<a href="qnaDelete.do?qbno=${view.qbno}" onclick="return confirm('本当に削除しますか')">消す</a>
		</div>
		
		<c:if test="${next.qbno != 0}">
			<a href="qnaview.do?qbno=${next.qbno}&pageNum=${cri.pageNum}&amount=${cri.amount}" class="btn_next">
				<span class="next_wrap">
				<strong>以降の書き込み</strong><span>${next.title}</span>
				</span>
				<i class="fa fa-angle-right"></i>
			</a>
		</c:if>
		<c:if test="${next.qbno == 0}">
			<a class="btn_next">
				<span class="next_wrap">
					<strong>最後の</strong><span>書き込みです</span>
				</span>
				<i class="fa fa-angle-right"></i>
			</a>
		</c:if>
	</div>
</div>
<div class="containaer">
	<div class="cmt-container">
		<div class="cmtCount">Comments:</div>
			<div class="cmt-box">
			<textarea class="cmt-area" rows="4" cols="" placeholder="コメントをご入力ください"></textarea>
			</div>
			<div class="cmt-buttons">
				<button type="button" id="butCmt" class="info cmt-insert" onclick="cmtWrite();">登録</button>
			</div>
		<div class="cmtList-box">
			<ul id="addcmt">
			<c:forEach var = "list" items = "${cmtList }">
				<li class="reply">
					<span><img src="../images/ceo.jpg" alt="" class="cmt-icon thumb_profile"></span>
					<span>
						<span class="cmtWriter">${list.writer }</span>
						<span class="cmtDate">${list.wdate }</span>
						<span class="cmtContent">${list.content }</span>
						<button style="float:right;" onclick=""></button>
					</span>
				</li>
		</c:forEach>
		</ul>
		</div>
	</div>
</div>

<script>
	function cmtWrite(){
		var bno = "${view.qbno}";
		var cmtContent = $(".cmt-area").val();
		var writer = "${sessionScope.userid}";
		
		if(cmtContent == ""){
			alert("コメントをご入力ください");
			return false;
		}
		if(writer == ""){
			alert("ログイン後にご入力ください");
			return false;
		}
		var cdData = {
			bno:bno,
			writer:writer,
			content:cmtContent
		}
		$.ajax({
			type:"post",
			url:"qnaCmt.do",
			data:cdData,
			success:function(result){
				if(result == 1){
					$(".cmt-area").val("");
					getCmtList(); //자바스크립트에서 함수호출
				}else{
					alert("登録失敗");
					return false;
				}
			}
		})
	}
	function getCmtList(){
		var output ="";
		var logId = "${sessionScope.userid}";
		var bno = "${view.qbno}"
		var cdData = {
				bno : bno
		}
		$.ajax({
			type:"get",
			url:"qnaCmt.do",
			data:cdData,// 보내는 데이터 타입
			dataType:"json", // 받는 데이터 타입
			success:function(result){
				for(var i in result){
					output +='<ul id="addcmt">';
					output +='<li class="reply">';
					output +='<span><img src="../images/ceo.jpg" alt="" class="cmt-icon thumb_profile"></span>';
					output +='<span>';
					output +='<span class="cmtWriter">'+result[i].writer+'</span>';
					output +='<span class="cmtDate">'+result[i].wdate+'</span>';
					output +='<span class="cmtContent">'+result[i].content+'</span>';
						if(result[i].writer == logId){
							output += '<button style = "float:right;" onclick="deleteCmt('+result[i].bno+')"></button>';
						}
					output +='</span>';
					output +='</li>';
					output +='</ul>';
				}
			$(".cmtList-box").html(output);
			}
		})
	}
</script>

<%@ include file="../footer.jsp"%>
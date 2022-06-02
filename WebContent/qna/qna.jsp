<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
	
<div class="gnb" style="padding-top:20px; text-align:center;">
		
	<div class="group">
		<h2>Q&A</h2>
	</div>
	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>総　書き込み件数<span>${count }</span>個</p>
			</div>
			<div class="search_group">
				<form name="myform" method="get" action="qna.do">
					<select name="sel" class="select">
						<option value="title">タイトル</option>
						<option value="content">内容</option>
					</select>
					<input type="text" name="word" class="search_word">
					<button class="btn_search" type="submit"><i class="fa fa-search"></i><span class="sr-only">検索"</span></button>
				</form>
			</div>
		</div> <!-- search end -->
			<div class="bord_list">
				<table class="bord_table" summary="この表はナンバー、タイトル、返答、ID、クリック数で構成されています">			
					<colgroup>
						<col width="100px">
						<col width="250px">
						<col width="100px">
						<col width="100px">
						<col width="100px">
					</colgroup>
					<thead>
						<tr>
							<th>ナンバー</th>
							<th>タイトル</th>
							<th>返答</th>
							<th>ID</th>
							<th>クリック数</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="num" value="${count - ((pageMaker.cri.pageNum-1) * 10) }"/>
					<c:forEach var="list" items="${qlist}">
						<tr>
							<td>${num}</td>
							<td class="title">
								<a href="qnaview.do?qbno=${list.qbno}&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}">${list.title}</a>
							</td>
							<td>${list.status}</td>
							<td>${list.writer}</td>
							<td>${list.viewcount}</td>
							<c:set var="num" value="${num-1}"/>
						</tr>
					</c:forEach>
					</tbody>
				</table>
	
			<div class="pagination">
			<c:if test="${pageMaker.prev}">
				<a href="${pageMaker.startPage-1}"><i class="fa  fa-angle-double-left"></i></a>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<a href="${num}" class="${pageMaker.cri.pageNum == num?'active':''}">${num}</a> <!-- active: 백그라운드 속성 -->
			</c:forEach>
			<c:if test="${pageMaker.next}">
				<a href="${pageMaker.endPage+1}"><i class="fa  fa-angle-double-right"></i></a>
			</c:if>
			<div>
				<a class="btn write" href="qnawrite.do" style="float:right; margin-top:12px; border:1px solid #ccc;">書き込み</a>
			</div>
				<form id="actionForm" action="qna.do" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" name="amount" value="10">
					<input type="hidden" name="sel" value="${pageMaker.cri.type}">
					<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
				</form>
			</div>
		</div><!--  -->
	</div>
</div>
	
<script>
	$(function() {
		$("#btn_qnawrite").on("click", function(){
			var userid="${sessionScope.userid}";
			if(!userid){
				alert("ログインしてください")
				$(location).attr("href","login.do");
			}else{
				$(location).attr("href","qnawrite.do");
			}
		})
		var actionForm = $("#actionForm");
		$(".pagination > a").on("click", function(e) {
			e.preventDefault();
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		})
	});
</script>


<%@ include file="../footer.jsp"%>







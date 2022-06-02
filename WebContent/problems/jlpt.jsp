<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

<div class="gnb" style="padding-top:20px; text-align:center;">
	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>総　書き込み件数 <span> ${count }</span>件</p>
			</div>
			<div class="search_group">
				<form name="myform" method="get" action="jlpt.do?jj"+jj>
					<select name="sel" class="select">
						<option value="title">タイトル</option>
						<option value="content">内容</option>
					</select>
					<input type="text" name="word" class="search_word">
					<button class="btn_search" type="submit"><i class="fa fa-search"></i><span class="sr-only">検索</span></button>
				</form>
			</div>
		</div> <!-- search end -->
		<div class="bord_list">
			<table class="bord_table" summary="이 표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>ナンバー</th>
					<th>タイトル</th>
					<th>語彙</th>
					<th>文法</th>
					<th>読解</th>
					<th>聴解</th>
					<th>作成者</th>
					<th>クリック数</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="${count - ((pageMaker.cri.pageNum-1) * 10)}"/>
				<c:forEach var="down" items="${list}">
				<tr>
					<td>${num}</td>
					<td class="title">
						<a href="jlptview.do?bno=${down.bno}&jj=${jj}&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}">${down.title}</a>
					</td>
					<td>
						<c:if test="${down.wordrealname != null}"></c:if>
						<a href="${pageContext.request.contextPath}/ndownload.do?file=${down.wordrealname}"><img src="images/file.png"></a>
					</td>
					<td><c:if test="${down.grammarrealname != null}"></c:if>
						<a href="${pageContext.request.contextPath}/ndownload.do?file=${down.grammarrealname}"><img src="images/file.png"></a>
					</td>
					<td><c:if test="${down.readingrealname != null}"></c:if>
						<a href="${pageContext.request.contextPath}/ndownload.do?file=${down.readingrealname}"><img src="images/file.png"></a>
					</td>
					<td><c:if test="${down.listeningrealname != null}"></c:if>
						<a href="${pageContext.request.contextPath}/ndownload.do?file=${down.listeningrealname}"><img src="images/file.png"></a>
					</td>
					<td>${down.writer}</td>
					<td>${down.viewcount}</td>
					
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
					<button class="btn write" onclick="location.href='jlptwrite.do?jj=${jj}'" style="float:right; margin-top:12px;">書き込み</button>
				</div>
				<form id="actionForm" action="jlpt.do" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" name="amount" value="10">
					<input type="hidden" name="sel" value="${pageMaker.cri.type}">
					<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
				</form>
			</div>
		</div>
	</div>
</div>

<!-- end contents -->
<script>
	$(function() {
		var actionForm = $("#actionForm");
		$(".paging > a").on("click", function(e) {
			e.preventDefault();
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		})
	});
</script>

<%@ include file="../footer.jsp"%>
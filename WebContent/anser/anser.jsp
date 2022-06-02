<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
	
<div class="gnb" style="padding-top:20px; text-align:center;">
		
	<div class="group">
	<h2>答え</h2>
	</div>
	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>総　書き込み件数　<span>${count}</span>件</p>
			</div>
			<div class="search_group">
			</div>
		</div> <!-- search end -->
			
		<div class="bord_list">
			<div class="gallery_wrap">
	   			<ul>
				<c:forEach var="list" items="${jlist}">
					<li>
						<a href="anserview.do" id="${list.bno}">
							<span style="display:block; height:282px; overflow:hidden;">
								<img src="${pageContext.request.contextPath}/upload/${list.imgurl}" alt="">
							</span>
						</a>
						<span class="title">${list.title}</span>
						<div class="gallery_count">
							<span class="count"><i class="fa fa-eye"></i></span>
							<span class="${list.bno}">${list.viewcount}</span>
						</div>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<form id="actionForm" action="anser.do" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
		<input type="hidden" name="amount" value="10">
		<input type="hidden" name="sel" value="${pageMaker.cri.type}">
		<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
	</form>
		        
</div>
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
			<button class="btn write" onclick="location.href='anserwrite.do'" style="float:right; margin-top:12px;">書き込み</button>
		</div>
	</div>
	
<script>
	$(function() {
		$(".gallery_wrap > ul > li > a").on("click",function(e) {
			e.preventDefault();
		var imgsrc = $(this).find("img").attr("src");//attr("src"); 속성 값 뽑아온다, find() 모든 자식 검색, children()바로 밑 자식 검색
	         
		console.log(imgsrc);
	         
		var no = $(this).attr("id");//자기자신의 id 속성의 값이 no에 저장된다
		console.log(no);
	         
		$.ajax({//비동기식 데이터처리 함수, 속성
			type: "post",
			url: "viewcount.do", //서블렛으로~
			data:{bno:no}, 
			success:function(data) {
				var count = data;//서블릿으로부터 매개변수 data를 받아 count 변수에 저장
				$(".gallery_wrap > ul > li > .gallery_count ."+no).html(count);
			},error:function(xhr, status, error) {
				alert("通信エラー");
			}
		})
		});
});
</script>
	
<%@ include file="../footer.jsp"%>
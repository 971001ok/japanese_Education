<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
	
<div class="gnb" style="padding-top:20px; text-align:center;">

	<h1>${jvo.title}</h1>
	<div class="link">
		<a href="${pageContext.request.contextPath}/ndownload.do?file=${jvo.wordrealname}">語彙</a><br>
		<a href="${pageContext.request.contextPath}/ndownload.do?file=${jvo.grammarrealname}">文法</a><br>
		<a href="${pageContext.request.contextPath}/ndownload.do?file=${jvo.readingrealname}">読解</a><br>
		<a href="${pageContext.request.contextPath}/ndownload.do?file=${jvo.listeningrealname}">聴解</a>
	</div>
	<div class="prev_next">
		<c:if test="${prev.bno != 0}">
			<a href="Noticeveiw.do?bno=${prev.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}" class="btn_prev">
				<i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
				<strong>以前の書き込み</strong><span>${prev.title}</span>
				</span>
			</a>
		</c:if>
		<c:if test="${prev.bno == 0}">
			<a class="btn_prev">
				<i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
				<strong>最後の</strong><span>書き込みです</span>
				</span>
			</a>
		</c:if>
		<div class="btn_3wrap">
			<a href="jlpt.do?jj=${jj}&pageNum=${cri.pageNum}&amount=${cri.amount}">リスト</a> 
			<a href="jlptEdit.do?jj=${jj}&bno=${view.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}">直す</a> 
			<a href="jlptDelete.do?jj=${jj}&bno=${view.bno}" onclick="return confirm('本当に削除しますか')">消す</a>
		</div>
		
		<c:if test="${next.bno != 0}">
			<a href="Noticeveiw.do?bno=${next.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}" class="btn_next">
				<span class="next_wrap">
				<strong>以降の書き込み</strong><span>${next.title}</span>
				</span>
				<i class="fa fa-angle-right"></i>
			</a>
		</c:if>
		<c:if test="${next.bno == 0}">
			<a class="btn_next">
				<span class="next_wrap">
					<strong>最後の</strong><span>書き込みです</span>
				</span>
				<i class="fa fa-angle-right"></i>
			</a>
		</c:if>
	</div>
</div>

<%@ include file="../footer.jsp"%>
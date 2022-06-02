<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>韓国人向けの日本語の勉強</title>
<link href="images/favicon.ico" rel="shertcut icon">

<link href="css/font-awesome.min.css" rel="stylesheet">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/slick.min.js"></script>

<script>
	$(function(){
		$(".navi > ul > li").hover(function(){
	    $(".navi > ul > li").removeClass("active");
	    $(this).addClass("active");
	    $(this).children(".sub-2depth").stop().slideDown(200);
	    },function(){//,:그렇지 않으면
	    $(".navi > ul > li").removeClass("active");
	    $(this).children(".sub-2depth").slideUp(300);
	    })
	})
</script>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet">
	<link href="css/sub.css" rel="stylesheet">
	<link href="css/slick.css" rel="stylesheet">
</head>
<body>
	<header class="header">
	
	
		<div class="topmenu">
			<nav class="jright">
				<ul>
				<c:choose>
					<c:when test="${empty userid}">
						<li><a href="jlogin.do">ログイン</a></li>
						<li><a href="term.do">会員登録</a></li>
						<li><a href="qna.do">Q&A</a></li>
					</c:when>
					<c:when test="${not empty userid}">
						<li><a href="jlogout.do">ログアウト</a></li>
						<li><a href="mypage.do">情報変更</a></li>
						<li><a href="qna.do">Q&A</a></li>
					</c:when>				
				</c:choose>
				</ul>
			</nav>
		</div>	
		
		<div class="gnb">
			<h1 class="logo"><a href="index.jsp"><img src="images/logo4.png" class="logoimg"></a></h1>
			<nav class="navi">
				<ul>
					<li>
						<a href="hiragana.do">日本語</a>
						<ul class="sub-2depth">
							<li><a href="hiragana.do">ひらがな</a></li>
							<li><a href="katakana.do">カタカナ</a></li>
							<li><a href="chinese_character.do">漢字</a></li>
						</ul>
					</li>
					<li>
						<a href="jlpt.do">テスト</a>
						<ul class="sub-2depth">
							<li><a href="jlptTest.do">JLPT</a></li>
							<li><a href="jpt.do">JPT</a></li>
						</ul>
					</li>
					<li>
						<a href="jlpt.do?jlpt=n1">問題</a>
						<ul class="sub-2depth">
							<li><a href="jlpt.do?jj=n1">JLPT N1</a></li> 
							<li><a href="jlpt.do?jj=n2">JLPT N2</a></li>
							<li><a href="jlpt.do?jj=n3">JLPT N3</a></li>
							<li><a href="jlpt.do?jj=n4">JLPT N4</a></li>
							<li><a href="jlpt.do?jj=n5">JLPT N5</a></li>
							<li><a href="anser.do">答え</a></li>
						</ul>
					</li>
					<li>
						<a href="first-grader.do">おとぎ話</a>
						<ul class="sub-2depth">
							<li><a href="first_grader.do">1年生</a></li>
							<li><a href="second_grader.do">2年生</a></li>
							<li><a href="third_grader.do">3年生</a></li>
							<li><a href="fourth_grader.do">4年生</a></li>
							<li><a href="fifth_grade.do">5年生</a></li>
							<li><a href="sixth_grader.do">6年生</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</header>
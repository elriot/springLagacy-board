<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script>
function popUp(w, h){
	 x = (screen.availWidth - w) / 2;
	 y = (screen.availHeight - h) / 2;
	 window.open('/movie/member/login', 'login','width='+w+', height='+h+', left='+x+', top='+y+',toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,copyhistory=no,resizable=no');
	}   
</script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Clean Blog - Start Bootstrap Theme</title>

    <!-- Bootstrap core CSS -->
    <link href="/movie/startbootstrap-clean-blog-gh-pages/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="/movie/startbootstrap-clean-blog-gh-pages/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="/movie/startbootstrap-clean-blog-gh-pages/css/clean-blog.min.css" rel="stylesheet">

  </head>

    <script src="/movie/startbootstrap-clean-blog-gh-pages/vendor/jquery/jquery.min.js"></script>
    <script src="/movie/startbootstrap-clean-blog-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" href="/movie/">Start Bootstrap</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="/movie/">메인화면</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/movie/" onclick="popUp(320, 370)">로그인</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/movie/book/select">영화 예매</a>
            </li>            
            <li class="nav-item"><!-- //하이퍼링크엥 슬래쉬없음 -->
              <a class="nav-link" href="/movie/member/add">회원가입</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/movie/admin/main">관리자메뉴</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

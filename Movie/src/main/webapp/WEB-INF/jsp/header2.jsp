<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
  	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
 	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<title>Movie</title>
</head>
<body>

<header class="topMenu">
    	<div class='zeta-menu-bar'>
  <ul class="zeta-menu">
    <li><a href="#">홈</a></li>
    <li><a href="#">1번 메뉴</a>
      <ul>
        <li><a href="#">1-A 메뉴</a>
          <ul>
            <li><a href="#">1-A-1 메뉴</a></li>
            
          </ul>
        </li>
        <li><a href="#">1-B 메뉴</a></li>
        <li><a href="#">1-C 메뉴</a>
        
        </li>
      </ul>
    </li>
    <li><a href="#">2번 메뉴</a>
   
    </li> 
    <li><a href="#">3번 메뉴</a></li> 
  </ul>
</div>
</header>
	<%-- <nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/movie">Movie</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/movie">메인</a></li>
				<li class="nav-item"><a class="nav-link" href="/movie/book/selectMovie">영화 예매</a></li>
				
				<li class="nav-item"><a class="nav-link" href="#">상영시간표</a></li>

					
 			<c:choose>
				<c:when test="${not empty mb_ID}">
				  <li class="nav-item">
				    <a class="nav-link" href="/movie/member/logout">로그아웃  </a>
				  </li>
				<li class="nav-item">
					 <a class="nav-link" href="/movie/member/myPage">마이페이지</a>
				</li>
				    <c:if test="${mb_ID eq 'admin'}">
				      <li class="nav-item">
				        <a class="nav-link" href="/movie/admin/main">
				                    관리자메뉴
				        </a>
					  </li>
				    </c:if>
				  
				</c:when>
				<c:otherwise>	
				  <li class="nav-item nav-link" data-toggle="modal" data-target="#myModal">
				  	로그인
				  </li>		
					<li class="nav-item">
						<!-- //하이퍼링크엥 슬래쉬없음 --> <a class="nav-link"
						href="/movie/member/add">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
			</ul>
			</div>
			</nav>
			 
 	<br> 
	<br> --%>
	
<!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
      
        <!-- <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Login</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
       </div>
	  <form action="/movie/member/login" method="post" name="w_form">
			<div class="modal-body">
				<div class="form-group">
					<input type="text" name="mb_ID" class="form-control"
						placeholder="Enter ID" required/>
				</div>
				<div class="form-group">
					<input type="password" name="mb_passwd" class="form-control"
						placeholder="Enter Password" required/>
				</div>
	            <input type="checkbox" value="remember-me"> 기억하기<br>
				<input type="submit" class="btn btn-lg btn-primary btn-block" value="로그인"/>
			</div>
		</form>
	
			<div class="modal-footer">
				<a href='/movie/member/searchIdPasswdForm'>아이디/비밀번호 찾기</a>		
				<input type="button" class="btn btn-danger" value="회원가입" onclick="location.href='/movie/member/add'"/>			
	        </div>
	        
	         

      </div>
    </div>
  </div>


 
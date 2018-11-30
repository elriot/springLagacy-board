<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../header.jsp"%>
<!-- Page Header -->
<header class="masthead" style="background-image: url('/movie/startbootstrap-clean-blog-gh-pages/img/home-bg.jpg')">
	 <div class="overlay"></div>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto"> 
				<div class="site-heading">
					<h1>select movie</h1>
					<span class="subheading"></span>
				</div>
 			</div>
		</div>
	</div>
</header> 


<form action="seat" method="post">
	<div class="form-group">
		<label>	영화 선택 :</label> <select class="form-control" name="mv_num">
			<c:choose>
				<c:when test="${list.size() > 0 }">
					<c:forEach var="m" items="${list }">
						<option value="${m.mv_num }">${m.mv_title }</option>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<option>상영중인 영화가 없습니다.</option>
				</c:otherwise>
			</c:choose>
		</select>
	</div>
상영관 입력 : <input type="text" name="tt_num"><br>
상영일 입력 : <input type="date" name="bk_wDate" min="${min}" max="${max}"><br>
상영시간 선택 : <input type="text" name="rg_time"><br>
<input type="submit">
</form>
<hr>
<%@include file="../footer.jsp"%>






<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../header.jsp"%>

<div class="container mt-3">

	<h2>My Page</h2>
	<ul class="nav nav-tabs nav-justified">
        <li class="nav-item"><a class="nav-link" href="#profile">프로필수정</a></li>
		<li class="nav-item"><a class="nav-link" href="#bookInfo">예약확인</a></li>
		<li class="nav-item"><a class="nav-link" href="#customerCenter">1:1문의</a></li>
		<li class="nav-item"><a class="nav-link" href="#delete">탈퇴</a></li>
	</ul>
	
	<!-- 프로필수정  명령어 update-->
	<div class="tab-content">
		<div id="profile" class="container tab-pane fade">
			<br>
			<div class="container">
				<%@include file="memberUpdate.jsp"%>
			</div>
		</div>

		
	<!-- 예약확인 명령어  bookInfo-->

		<div id="bookInfo" class="container tab-pane fade">
				<%@include file="memberBookInfo.jsp"%>
		</div>
		<div id="customerCenter" class="container tab-pane fade">
			<br>
			<h3>1:1문의</h3>
			<p>건의사항 및 문의사항 있으시면 글 남겨주세요.</p>
			<br>
			<div class="container">
				<form action="../customerCenter" method="post" name="form">
					<div class="form-group">
						<textarea style="resize:none" name="cc_question" class="form-control" rows="5" placeholder="글을 남겨주세요"></textarea>							
					</div>
					<input type="submit" class="btn btn-primary" value="전송하기" />
				</form>
			</div>
		</div>


			<!-- 회원탈퇴  명령어 delete-->

			<div id="delete" class="container tab-pane fade">
				<div class="container">
					<br>
					<%@include file="memberDelete.jsp"%>
				</div>

			</div>

		</div>

	</div>


<script>
$(document).ready(function() {
	$(".nav-tabs a").click(function() {
		$(this).tab('show');
	});
});
</script>

<%@include file="../footer.jsp"%>


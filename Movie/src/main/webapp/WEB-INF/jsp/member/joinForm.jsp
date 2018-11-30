<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../header.jsp"%>
<script>
$(document).ready(function () { 
	
	$('#id').keyup(function () {
		
		var id = $(this).val();
		
		$.ajax({
			url: 'member/chkDupId',
			data: {id: id},
			success: function (result) {
				
				
				if (result.count == 0) {
					$('#dupIdMsg').text('사용가능한 아이디입니다.').css('color', 'green');
				} else { // result.count == 1
					$('#dupIdMsg').text('이미 사용중인 아이디입니다.').css('color', 'red');
				}
			}
		});
	});
	
	$('#emailBtn').click(function () {
		var userEmail = $('#userEmail').val();
		
		$.ajax({
			url: 'member/mail',
			data: {
				'email' : email
			},
			success: function (result) {
				$('#checkCode').val(result);
			}
		});
	});
	
	$('#check_btn').click(function () {
		
		if($('#checkCode').val() == $('#code').val()){
			alert('인증되었습니다.');
			$('#email_code').val('1');
		}else{
			alert('인증번호가 틀렸습니다.');
		}
		
	})
	
// 	function DosignUp(){
		
// 		var userid = ${"#id"}.val();
// 		var userpwd = ${"#passwd"}.val();
// 		var userpwdDp = ${"#passwdDp"}.val();
// 		var userName = ${"#name"}.val();
// 		var userEmail = ${"#email"}.val();
// 		var EmailCode = ${"#code"}.val();
// 		var userPhone = ${"#phonN"}.val();
		
// 		if(userid.length == 0){
// 			alert('아이디를 입력해 주세요.');
// 			$("#id").focus();
// 			return false;
// 		}
		
// 		if(userpwd.length == 0){
// 			alert('비밀번호를 입력해 주세요.');
// 			$("#passwd").focus();
// 			return false;
// 		}
		
// 		if(userpwd != userpwdDp){
// 			alert('비밀번호가 서로 다릅니다.');
			
// 		}
		
		
// 	}
	
});
</script>
<!-- Page Header -->
<header class="masthead"
	style="background-image: url('/movie/startbootstrap-clean-blog-gh-pages/img/home-bg.jpg')">
	<div class="overlay"></div>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="site-heading">
					<h1>JOin</h1>
					<span class="subheading">A Blog Theme by Start Bootstrap</span>
				</div>
			</div>
		</div>
	</div>
</header>


<!-- Main Content -->
<div class="container">
	<div class="row">
		<div class="col-lg-8 col-md-10 mx-auto">
			<div class="post-preview">
				<form action="add" method="post" name="frm">
					<input type="text" name="mb_ID" id="id" placeholder="ID"> <span
						id="dupIdMsg"></span> <br> <input type="password"
						name="mb_passwd" id="passwd" placeholder="PASSWORD"><br>
					<input type="password" id="passwdDp" placeholder="PASSWORD">
					<!-- 	<span id=""></span> -->
					<input type="text" name="mb_name" id="name" placeholder="NAME"><br>
					<input type="email" name="mb_email" id="email"
						placeholder="ex)email@.com"> <input type="button"
						name="emailBtn" id="emailBtn" value="코드발송"><br> <input
						type="hidden" name="" id="checkCode" value="0"> <input
						type="text" name="mb_emailN" id="code"
						placeholder="Verification code"><br> <input
						type="button" id="check_btn" value="코드인증"><br> <input
						type="hidden" name="" id="email_code" value="0"> <input
						type="text" name="mb_phone" id="phonN"
						placeholder="ex)010-4567-1234"><br> <input
						type="hidden" name="mb_grade" value="bronze"> <input
						type="hidden" name="mb_point" value="1000"> <input
						type="submit" value="등록">
				</form>
			</div>
		</div>
	</div>
</div>


<hr>
<%@include file="../footer.jsp"%>


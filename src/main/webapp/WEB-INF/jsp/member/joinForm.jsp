<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../header.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function () { 
	
	$('#id').keyup(function () {
		
		var id = $('#id').val();
		
		$.ajax({
			url:'../api/member/chkDupId',
			data: {id:id},
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
		
		var userEmail = $('#email').val();
		alert(userEmail);
		$.ajax({
			url: '../mail/mail',
			data: {
				email: userEmail
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
	});

	$('#email').keyup(function () {
	
		var email = $('#email').val();
			console.log("이메일중복검사");
		$.ajax({
			url: '../api/member/chkDupEmail',
			data: {email:email},
			success: function (result) {
				if (result.count == 0) {// 아이디가 없을경우
					$('#dupEmailMsg').text('사용 가능한 이메일입니다.').css('color', 'green');
				} else { // result.count == 1 이미 아이디가 있을경우
					$('#dupEmailMsg').text('이미 사용중인 이메일입니다.').css('color', 'red');
					return false;
				}
			}
		});
	});
});


function validate() {
    var re = /^[a-zA-Z0-9]{4,12}$/ 
    // 아이디와 패스워드가 적합한지 검사할 정규식
    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    // 이메일이 적합한지 검사할 정규식
    var re3 = /^[a-zA-Z]{1,30}$/ 
    // 이름 정규식
    var regExp = /^\d{3}-\d{3,4}-\d{4}$/;

    // 폰번호 정규식
    
    var id = document.getElementById("id");
    var pw = document.getElementById("passwd");
    var pwD = document.getElementById("passwdDp");
    var name = document.getElementById("name");
    var email = document.getElementById("email");
    var emailChk = document.getElementById("code");
//     var code = document.getElementById("checkCode");
    var phone = document.getElementById("phonN");
    
    

    // ------------ 이메일 까지 -----------

    if(!check(re,id,"아이디는 4~12자의 영문, 대소문자와 숫자로만 입력해 주세요")) {
        return false;
    }

    if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력해 주세요")) {
        return false;
    }

    if(pw.value != pwD.value) {
        alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
        pwD.value = "";
        pwD.focus();
        return false;
    }
    
    if(!check(re,regExp,"- 를 포함한 숫자만 입력해 주세요.")){
    
    	return false;
    }
    
    if(name.value == ""){
    	alert("이름을 입력해 주세요")
    	name.focus();
    	return false;
    }

    if(email.value=="") {
        alert("이메일을 입력해 주세요");
        email.focus();
        return false;
    }

    //code, checkCode
    if(emailChk.value != $('#checkCode').val()){
    	alert('인증코드가 틀립니다.');
    	emailChk.value = "";
    	emailChk.focus();
    	return false;
    }
	
    if(name.value=="") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    }
    
	
    alert("회원가입이 완료되었습니다.");
}

function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    //return false;
}

function check1(re2, what, message) {
    if(re2.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    //return false;
}
</script>
<section id="about">
<h1 class="text-center">회원가입</h1>
<hr>
<form action="add" method="post" name="frm">
<div class="container">
	<div class="row">
		<div class="col-lg-8 col-md-10 mx-auto">
			<div class="post-preview">
				<input type="text" name="mb_ID" id="id" class="form-control" placeholder="Enter ID"><span id="dupIdMsg"></span><br>
				<input type="password" name="mb_passwd" id="passwd" class="form-control"placeholder="Enter Password">
				<input type="password" id="passwdDp" class="form-control"placeholder="Please Enter password again"><br>
				<input type="text" name="mb_name" id="name" class="form-control"placeholder="Enter name"><br>
				<input type="email" name="mb_email" id="email" class="form-control"placeholder="ex)email@.com">
				<span id="dupEmailMsg"></span><br>
				<input type="button" name="emailBtn" id="emailBtn"class="btn btn-primary" value="코드발송"><br><br>
				<input type="hidden" name="" id="checkCode" value="0"><input type="text" name="mb_emailN" id="code" class="form-control"placeholder="Enter Verification code"><input type="button" id="check_btn" class="btn btn-primary" value="코드인증"><br><br> 
				<input type="hidden" name="" id="email_code" class="form-control"value="0"> 
				<input type="text" name="mb_phone" id="phonN" class="form-control"placeholder="ex)010-4567-1234"><br> 
				<input type="hidden" name="mb_grade" class="form-control"value="bronze"> 
				<input type="hidden" name="mb_point" class="form-control"value="1000">
				<input type="reset" class="btn btn-danger" value="Reset"> 
				<input type="submit" class="btn btn-primary" value="Sign Up">
			</div>				
		</div>
		
	</div>
</div>
</form>
</section>
<%@include file="../footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/ki.css" />
<title>Insert title here</title>
<style>

	.ki { background-image: url(../gif/ki.gif);
 		   background-size: 125%;
		    }

       
</style>

<!--  로딩 후 페이지 이동  -->
<script>
	function windows(){
		setTimeout("go()", 1200);
	}
	function go(){
		location.href="ki.jsp";
	}
</script>

</head>
<body class="ki" onload="windows()">

</body>
</html>
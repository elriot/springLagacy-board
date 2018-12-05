<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/bt.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!--  로딩 후 페이지 이동  -->
<script>
	function windows(){
		setTimeout("go()", 1250);
	}
	function go(){
		location.href="bt.jsp";
	}
</script>
<style>
.bt { background-image: url(../gif/bt.gif);
 		   background-size: 125%;
		    }

</style>
</head>
<body class="bt" onload="windows()">

</body>
</html>
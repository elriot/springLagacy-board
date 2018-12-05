<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/cl.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!--  로딩 후 페이지 이동  -->
<script>
	function windows(){
		setTimeout("go()", 1200);
	}
	function go(){
		location.href="cl.jsp";
	}
</script>
<style>

	.cl { background-image: url(../gif/cl.gif);
 		   background-size: 125%;
		    }

       
</style>
</head>
<body class="cl" onload="windows()">

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="reset.css">
<!--기본적인 디자인을 리셋시키는 css -->
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<style>



body {
   margin: 0;
}

.scene {
   background-attachment : fixed;
    background-size : cover;   
   height: 100vh;
   overflow: hidden;
}
 .scene.one { 
 background-image: url(../img/cl1.png);
 background-size: 700px;}
 
 .scene.two { 
 background-image: url(../img/cl2.jpg); 
 }
 
 .scene.three {
 background-image: url(../img/cl3.png);

 }
  .scene.four { 
 background-image: url(../img/cl4.jpg); 
  
  }
 

#videobcg
{ 

left: 95px;
/* min-width:   100vh; */
 min-height:  100vh;
width: auto;
 height: 200px;  
z-index: -1000;
overflow: hidden;
loop:loop;
width: 700px; 
display: block; 
margin: 0 auto;
} 




</style>

<body>


<div>
 <Section class="scene one">
      <video id="videobcg" preload="auto" autoplay="autoplay" muted="muted" controls="controls">
	<source src="../video/cl1.mp4" type="video/mp4"></source>
</video>      
   </Section>
   </div>
   
   <Section class="scene two">
   </Section>
   
   <Section class="scene three">
   </Section>
   
   <Section class="scene four">
   </Section>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/main.css" />

<title>여행가자!</title>
</head>

<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
    
    <script>
   // Hide Header on on scroll down
var didScroll;
var lastScrollTop = 0;
var delta = 5;
var navbarHeight = $('header').outerHeight();

$(window).scroll(function(event){
    didScroll = true;
});

setInterval(function() {
    if (didScroll) {
        hasScrolled();
        didScroll = false;
    }
}, 250);

function hasScrolled() {
    var st = $(this).scrollTop();
    
    // Make sure they scroll more than delta
    if(Math.abs(lastScrollTop - st) <= delta)
        return;
    
    // If they scrolled down and are past the navbar, add class .nav-up.
    // This is necessary so you never see what is "behind" the navbar.
    if (st > lastScrollTop && st > navbarHeight){
        // Scroll Down
        $('header').removeClass('.topMenu').addClass('nav-up');
    } else {
        // Scroll Up
        if(st + $(window).height() < $(document).height()) {
            $('header').removeClass('nav-up').addClass('.topMenu');
        }
    }
    
    lastScrollTop = st;
}
</script>

<script src="//code.jquery.com/jquery.min.js"></script>
<script>
$(function(){
  $(".zeta-menu li").hover(function(){
    $('ul:first',this).show();
  }, function(){
    $('ul:first',this).hide();
  });
  $(".zeta-menu>li:has(ul)>a").each( function() {
    $(this).html( $(this).html()+' &or;' );
  });
  $(".zeta-menu ul li:has(ul)")
    .find("a:first")
    .append("<p style='float:right;margin:-3px'>&#9656;</p>");
});


</script>



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
<div class="container">
<h1></h1>
<p></p>
<pre>












</pre>
 <a href="cll.jsp" target="_blank">
<figure class="snip1132">
  <img src="../img/cl1.png" alt="sample22" /> 
</figure>
</a>

<a href="kil.jsp" target="_black">
<figure class="snip1132">
  <img src="../img/k1.jpg" alt="sample22" />
</figure>
</a>

<a href="btl.jsp" target="_black">
<figure class="snip1132">
  <img src="../img/bt1.jpg" alt="sample22" />
</figure>
</a>

</div>
</body>
</html>
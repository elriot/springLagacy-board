<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>No.</th>
				<th>제목</th> 
				<th>예약일</th>
				<th>상영일</th>
				<th>상영관</th>
				<th>좌석정보</th>
				<th>취소</th>
			</tr>
		</thead>
		<tbody>	
		<c:choose>
			<c:when test="${not empty bkList}">		
			<c:set var="i" value="1"/>	
			<c:forEach var="item" items="${bkList}">
				<tr>
					<td>${i}</td>
					<td>${item.mv_title}</td>
					<td>${item.bk_date}</td>
					<td>${item.bk_wDate} &nbsp; ${item.mv_time}</td>
					<td>${item.tt_num }</td>					
					<td>
						<c:set var="mv_num" value="${item.mv_num}"/>
						<c:forEach var="list" items="${requestScope.map}" varStatus="num">												 
				 		   <c:forEach var="item2" items="${list.value}">
				 		   <c:set var="i" value="1"/>
				 		   	<c:if test="${mv_num eq list.key }">
				 		   			<c:if test="${i%5==0}">
										<br>
									</c:if>	
				 		   		<c:out value="${item2.tt_seatNum}"/>
	 		   					<c:set var="i" value="${i+1}"/>		
				 		   </c:if>
					   </c:forEach> 				    
			  </c:forEach>
					
					
					
					
					
					
					</td>															
					<td>예약취소</td>		
				</tr>
				<c:set var="i" value="${i+1}"/>
			</c:forEach>

			</c:when>
		</c:choose>
		
<%-- 		
		<c:choose>
			<c:when test="${not empty bkList}">		
			<c:set var="i" value="1"/>	
			<c:forEach var="item" items="${bkList}">
				<tr>
					<td>${i}</td>
					<td>${item.mv_title}</td>
					<td>${item.bk_date}</td>
					<td>${item.bk_wDate} &nbsp; ${item.mv_time}</td>
					<td>${item.tt_num }</td>					
					<th><a data-toggle="modal" data-target="#myModal2">좌석보기</a></th>															
					<td>예약취소</td>		
				</tr>
				<c:set var="i" value="${i+1}"/>
			</c:forEach>

			</c:when>
		</c:choose> --%>
		</tbody>	
	</table>
</div>


<!-- The Modal -->
  <div class="modal fade" id="myModal2">
    <div class="modal-dialog">
      <div class="modal-content">
      
      
        <!-- <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">예약 정보</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
       </div>

       <div class="modal-body">
 	<table class="table table-hover">
		<thead>
			<tr>
				<th>mv_num</th>
				<th>좌석정보</th>
				
			</tr>
		</thead>
		<tbody>	
		<c:choose>
			<c:when test="${not empty bkList}">		
			<c:forEach var="item1" items="${bkList}">
				<tr>
					<td><c:set var="mv_num" value="${item.mv_num}"/>
					<c:out value="${item11.mv_title}"/>
					<c:forEach var="list" items="${requestScope.map}" varStatus="num">						  
						  <tr>
						  <td>
						  
<%--  						  <c:if test="${list.key eq item.tt_seatNum}"> <!--map.key는 key값을 출력한다... 51/43/44 --> --%>
				 		   <c:forEach var="item2" items="${list.value}">
				 		   	<c:if test="${mv_num eq list.key }">
						 <%--  <c:out value="${mv_num}"/> --%><c:out value="${item2.tt_seatNum}"/>
				 		   
				 		   </c:if>
						   </c:forEach> 
<%-- 						   </c:if>  --%>
						   </td>
						   </tr>
						     
					  </c:forEach>
					 </td>
					
																			

				</tr>
				<c:set var="i" value="${i+1}"/>
			</c:forEach>

			</c:when>
		</c:choose>
		</tbody>	
	</table>

       </div>


            
             

      </div>
    </div>
  </div>
  
  
  
<%--   
유효한 코드
    <table class="table table-hover">
		<thead>
			<tr>
				<th>좌석번호</th>				
			</tr>
		</thead>
		<tbody>	
		 <c:forEach var="list" items="${requestScope.map}" varStatus="num">
		  <tr>
		  <td>${num.count} <!-- 1, 2, 3 순번 --> 		 
		  <td>${list.key} <!--map.key는 key값을 출력한다... 51/43/44 -->
		 <!--map.value로 이중 forEach문을 실행한다..   -->
 		   <c:forEach var="item" items="${list.value}">
		  <td>${item.tt_seatNum} </td>
		   </c:forEach> 
		  </tr>   
		  </c:forEach>

		</tbody>	
	</table>
 --%>
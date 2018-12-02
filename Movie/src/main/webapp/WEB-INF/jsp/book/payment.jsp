<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../header.jsp"%>
<c:set var="i" value="1"/>
<c:set var="totalPrice" value="0"/>	

<form action="complete" method="post">
<input type="hidden" name="mv_title" value="${mv_title}">
<input type="hidden" name="mv_num" value="${bookVO.mv_num}">
<input type="hidden" name="tt_num" value="${bookVO.tt_num}">
<input type="hidden" name="bk_wDate" value="${bookVO.bk_wDate}">
<input type="hidden" name="mv_time" value="${bookVO.mv_time}">
<input type="hidden" name="mv_num" value="${bookVO.mv_num}">

	<div class="form-group">
			${bookVO.bk_wDate} ${bookVO.mv_time} : ${mv_title} (상영관 : ${bookVO.tt_num}) <br><br><br>
			<c:choose>

				<c:when test="${list.size() > 0 }">
					<c:forEach var="list" items="${list }">
						<b>${i}</b> <b>좌석</b> ${list.tt_seatNum} : <b>금액</b> ${list.bk_price} <br><br>
						<input type="hidden" name="tt_seatNum" value="${list.tt_seatNum}">
						<c:set var="i" value="${i+1}"/>
						<c:set var="totalPrice" value="${totalPrice+list.bk_price}"/>
					</c:forEach>
					<br>
				</c:when>
				<c:otherwise>
					선택한 좌석이 없습니다.
				</c:otherwise>
			</c:choose>
			<hr>
			<b>총 금액</b>   ${totalPrice}
	</div>
<input type="submit" value="결제">
</form>
<input type="button" value="좌석 다시 선택" onclick="history.back()">
<hr>
<%@include file="../footer.jsp"%>






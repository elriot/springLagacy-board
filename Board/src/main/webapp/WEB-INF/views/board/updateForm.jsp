<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="/resources/editor/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>

<%@include file="../header.jsp" %>

<div class="container">
  <form action="/board/update" method="post" name="w_form">
  	<input type="hidden" value="${boardVO.bNum}" name="bNum">
  	<!-- bNum, bTitle, bContent를 update로 전달 -->
    <div class="form-group">
      <input type="text" name="bTitle" class="form-control" value="${boardVO.bTitle}">
      <!-- name값을 VO의 변수명과 일치시켜야 함 -->      
    </div>
    <div class="form-group">
      <textarea name="bContent" id="textAreaContent" style="width: 100%" rows="15" cols="80">
      ${boardVO.bContent}
      </textarea>
    </div>
  
    <button type="button" class="btn btn-primary" onclick="submitContents(this)">수정하기</button>
  </form>
</div>

<!-- Naver Smart Editor 2 -->
<script>
  var form = document.w_form;
  var oEditors = [];
  // 함수 호출
  nhn.husky.EZCreator.createInIFrame({
      oAppRef: oEditors,
      elPlaceHolder: "textAreaContent",
      sSkinURI: "/resources/editor/SmartEditor2Skin.html",
      fCreator: "createSEditor2"
  });
   
  // submit
  function submitContents(elClickedObj) {
      // 에디터의 내용이 textarea에 적용된다.
      oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", [ ]);
      var con = document.w_form.bContent;
      con.value = document.getElementById("textAreaContent").value;
      try {
          elClickedObj.form.submit();
      } catch(e) {
       
      }
  }
   
  // textArea에 이미지 첨부
  function pasteHTML(filepath){
      var sHTML = '<img src="/resources/editor/upload/'+ filepath + '">';
	  oEditors.getById["textAreaContent"].exec("PASTE_HTML", [ sHTML ]);
  }
</script>


<%@include file="../footer.jsp" %>
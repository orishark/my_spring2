<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
/* 
function sign_in_check(){

	var user_id = $("#userId").val();
	var password = $("#password").val();

	if(user_id==""){
		alert("아이디를 입력하세요.");
		$("#userId").focus();
		return;
	}
	if(password==""){
		alert("비밀번호를 입력하세요.");
		$("#password").focus();
		return;
	}
} */
$(function(){

	$("#sign_in_btn").click(function(){
		
		var user_id = $("#userId").val();
		var password = $("#password").val();
	
		if(user_id==""){
			alert("아이디를 입력하세요.");
			$("#userId").focus();
			return;
		}
		if(password==""){
			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return;
		}
			
		document.sign_in_form.action="${path}/sign_in_check.do";
		document.sign_in_form.submit();
		
	});
});
</script>
</head>
<body>
<div class="container" id="main">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="sign_in_form" id="sign_in_form" method="post">
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <input class="form-control" id="userId" name="user_id" placeholder="User ID">
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="Password">
              </div>
              <button type="button" id="sign_in_btn" class="btn btn-success clearfix pull-right"">로그인</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>
</body>
</html>
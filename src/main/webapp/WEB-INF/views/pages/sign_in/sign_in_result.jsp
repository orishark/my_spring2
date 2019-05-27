<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
	
	if(${message == 'success'}){
		alert("로그인 되셨습니다.");
		location.href="${path}/";
	}
	else if(${message == 'error'}){
		alert("아이디나 비밀번호가 일치하지 않습니다.");
		location.href="${path}/sign_in.do";
	}
	else if(${message == 'logout'}){
		alert("로그아웃 되셨습니다.");
		location.href="${path}/sign_in.do";
	}
});

</script>
</head>
<body>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/static/js/preview_image.js"></script>
<script>

var id_check_result = "no";

$(function(){
	
	$("#userId").change(function(){
		id_check_result = "no";
	});
	
	$("#sign_up_btn").click(function(){
		
		var name = $("#name");
		var user_id = $("#userId");
		var password = $("#password").val();
		var confirm_password = $("#confirmPassword").val();
		var email = $("#email"); 
		var exp;
			
		if(name.val() == ""){
			
			alert("이름을 입력하세요.");
			name.focus();
			return;
			
		}else{
			
			exp = /^[가-핳\x20]{3,10}$/;
			
			if(!exp.test(name.val())){
				alert("이름은 한글 3~10자 이내로 입력하세요.");
				name.focus();
				return;
			}
		}
		
		if(user_id.val() == ""){
			
			alert("아이디를 입력하세요.");
			user_id.focus();
			return;
		}else{
			
			exp = /^[A-Za-z0-9]{4,10}$/;
			if(!exp.test(user_id.val())){
				alert("아이디는 영문자, 숫자 4~10자리로 입력하세요.");
				user_id.focus();
				return;
			}
			
			if( id_check_result == "no"){
				alert("아이디 중복검사를 해주세요.");
				return;
			}
		}
		
		if(password == ""){
			
			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return;
		}
		
		if(confirm_password == ""){
			
			alert("비밀번호을 한번 더 입력하세요.");
			$("#confirmPassword").focus();
			return;
		}
		
		if(password != confirm_password){
			
			alert("설정한 비밀번호가 일치하지 않습니다.");
			$("#password").focus();
			return;
		}
		
		if(email.val() != ""){
			
			exp = /^[a-z0-9]{2,}@[a-z0-9]{2,}\.[a-z]{2,}$/;
			
			if(!exp.test(email.val())){
				alert("잘못된 이메일 형식입니다.");
				email.focus();
				return;
			}
		}
	
		alert("성공적으로 회원가입이 되셨습니다.");
		document.sign_up.action="${path}/addMember.do";
		document.sign_up.submit();
	});
	
	$("#userid_check_btn").click(function(){
		
		var user_id = $("#userId");
		
		if(user_id.val() == ""){
			
			alert("아이디를 입력하세요.");
			user_id.focus();
			return;
			
		}else{
			
			exp = /^[A-Za-z0-9]{4,10}$/;
			
			if(!exp.test(user_id.val())){
				alert("아이디는 영문자, 숫자 4~10자리로 입력하세요.");
				user_id.focus();
				return;
			}
		}

		$.ajax({
			
			type: "post",
			url: "${path}/userId_check.do?user_id=" + user_id.val(),
			success: function(data){
				
				if(data == "success"){
					alert("사용 가능한 아이디입니다.");
					$("#password").focus();
				}
				else{
					alert("이미 사용중인 아이디입니다.");
					$("#userId").val("");
					$("#userId").focus();
				}
				
				id_check_result = "yes";
			}
		});
	});
});

</script>
</head>
<body>
<div class="container" id="main">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="sign_up" method="post" enctype="multipart/form-data">
          	  <div class="form-group">
                  <label for="name">이름</label>
                  <input class="form-control" id="name" name="name" placeholder="name">
              </div>
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <button type="button" id="userid_check_btn">중복검사</button>
                  <input class="form-control" id="userId" name="user_id" placeholder="user id">
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="password">
              </div>
              <div class="form-group">
                  <label for="password">비밀번호 확인</label>
                  <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="check password">
              </div>
              <div class="form-group">
                  <label for="profile_image">프로필 이미지</label>
                  <img src="/static/images/80-text.png" id="preview" width="80" height="80"></img>
                  <input type="file" id="profile_image" name="profile_image" accept=".jpg,.jpeg,.png,.gif">
              </div>
              <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="email" class="form-control" id="email" name="email" placeholder="Email">
              </div>
              <button type="button" class="btn btn-success clearfix pull-right" id="sign_up_btn">회원가입</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>
</body>
</html>